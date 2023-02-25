package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.AhmedGuedri.Repositories.DetailedOrdersRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.OrdersRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.ProductsRepository;
import tn.esprit.spring.AhmedGuedri.entities.DetailedOrders;
import tn.esprit.spring.AhmedGuedri.entities.Orders;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService{

    DetailedOrdersRepository detailedOrdersRepository;

    OrdersRepository ordersRepository;
    @Autowired
    ProductsRepository productsRepository;
    private EntityManager entityManager;
    @Override
    public List<Products> retrieveAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Products addOrUpdateProduct(Products p) {
        return productsRepository.save(p);
    }

    @Override
    public Products getProduct(Long IdProducts) {
        return productsRepository.findById(IdProducts).orElse(null);
    }

    @Override
    public void removeProduct(Long IdProducts) {
        productsRepository.deleteById(IdProducts);
    }

    @Transactional
    @Override
    @Scheduled(fixedRate = 60000) // run every minute
    public void addOrUpdatedetailedOrders() {
        detailedOrdersRepository.deleteAll();
        detailedOrdersRepository.resetId();
        List<Orders> orders =ordersRepository.findAll();
        for (Orders order : orders){
            DetailedOrders detailedOrders = new DetailedOrders();
            Long ordernum = order.getIdOrders();
            System.out.println("ordernum = " + ordernum);
            Long prod = order.getProductsList().get(0).getIdProducts();
            System.out.println("prod = " + prod);
            Long supp = order.getProductsList().get(0).getUserProducts().get(0).getId();
            System.out.println("supp = " + supp);
            Float price = order.getProductsList().get(0).getPrice();
            System.out.println("price = " + price);
            Date date = order.getBroughtDate();
            System.out.println("date = " + date);
            detailedOrders.setOrdernumber(ordernum);
            detailedOrders.setProduct(prod);
            detailedOrders.setSupplier(supp);
            detailedOrders.setPrice(price);
            detailedOrders.setBoughtDate(date);
            detailedOrdersRepository.save(detailedOrders);
        }
    }
    @Override
    public List<DetailedOrders> getDetailedOrdersbyDaterange(Date startdate, Date enddate, Long Supplier){
        List<DetailedOrders> dorders = (List<DetailedOrders>) detailedOrdersRepository.findAll();
        List<DetailedOrders> detailedOrders = new ArrayList<>();
        for(DetailedOrders dorder : dorders){
            if(dorder.getBoughtDate().after(startdate) && dorder.getBoughtDate().before(enddate) && (dorder.getSupplier()) == Supplier){
                detailedOrders.add(dorder);
            }
        }

        return detailedOrders;
    }
    public void resetId() {
        Query resetQuery = entityManager.createNativeQuery("ALTER TABLE detailed_orders AUTO_INCREMENT = 1");
        resetQuery.executeUpdate();
    }
}
