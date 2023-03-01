package tn.esprit.spring.AhmedGuedri.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repository.OrderRepo;
import tn.esprit.spring.AhmedGuedri.Repository.PanierRepo;
import tn.esprit.spring.AhmedGuedri.Repository.ProductRepo;
import tn.esprit.spring.AhmedGuedri.entities.*;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    PanierRepo panierRepo;
    @Autowired
    ProductRepo productRepo;

    @Override
    public List<Orders> retrieveAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Orders updateOrders(Orders orders) {
        return orderRepo.save(orders);
    }

    @Override
    public void deleteOrders(Long id) {
         orderRepo.deleteById(id);
    }

    @Override
    public Orders addOrders(Orders orders) {
        return orderRepo.save(orders);
    }

    @Override
    public Orders retrieveOrders(Long id) {
        Orders o = orderRepo.findById(id).orElse(null);
        return o;
    }

    @Override
    public Orders addPanierToOrder(Long panierId, Long orderId) {
        Panier panier = panierRepo.findById(panierId).orElse(null);
        Orders order = orderRepo.findById(orderId).orElse(null);
            order.setPanier(panier);
            orderRepo.save(order);

        return order;
    }
    @Override
    public Float TotalOrdersTVA (Long orderId){
        Orders order = orderRepo.findById(orderId).orElse(null);
        float total = 0;
        total = (float) ((order.getPanier().getTotalPrice()*order.getTax())+ order.getPanier().getTotalPrice());
        return total;
    }
}
