package tn.esprit.spring.AhmedGuedri.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.PaymentRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.ProductsRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.ShoppingCartRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    tn.esprit.spring.AhmedGuedri.Repository.OrderRepository orderRepo;
    @Autowired
    ShoppingCartRepository panierRepo;
    @Autowired
    ProductsRepository productRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    PaymentRepository paymentRepository;

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
    public Orders addOrders(Orders orders ,Long iduser) {
        User user = userRepo.findById(iduser).get();

        //update user
        List<Orders> ListOrders=new ArrayList<>(user.getUser_orders());
        System.out.println("SIZE : "+user.getShoppingCart().getProductsList().size());


        //loop example
        for (Products p : user.getShoppingCart().getProductsList()) {
            ListOrders.add(orders);
            Orders o=new Orders();
            o.setConfirmation(orders.isConfirmation());
            o.setStatusOrders(orders.getStatusOrders());
            o.setBroughtDate(orders.getBroughtDate());



           // o.setShoppingCart(user.getShoppingCart());
            orderRepo.save(o);
        }
        user.setUser_orders(ListOrders);
        Products p = new Products();
        p.setProduct_order(ListOrders);
        productRepo.save(p);
        userRepo.save(user);
        //orders.setProductsList();
        //orders.setShoppingCart(user.getShoppingCart());
        //userRepo.save(user);
        //end update
        //CHANGEME
       // orders.setUser(user);
        return orders;
    }

    @Override
    public Orders retrieveOrders(Long id) {
        Orders o = orderRepo.findById(id).orElse(null);
        return o;
    }

    @Override
    public Orders addPanierToOrder(Long panierId, Long orderId) {
        ShoppingCart panier = panierRepo.findById(panierId).orElse(null);
        Orders order = orderRepo.findById(orderId).orElse(null);
           // order.setPanier(panier);
           // order.setTotal(panier.getTotalPrice());
            orderRepo.save(order);

        return order;
    }
    @Override
    public Float TotalOrdersTVA (Long orderId){
        Orders order = orderRepo.findById(orderId).orElse(null);
        float total = 0;
       // total = (float) ((order.getPanier().getTotalPrice()*order.getTax())+ order.getPanier().getTotalPrice());
        return total;
    }

}
