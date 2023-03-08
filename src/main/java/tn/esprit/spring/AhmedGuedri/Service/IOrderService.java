package tn.esprit.spring.AhmedGuedri.Service;

import tn.esprit.spring.AhmedGuedri.entities.Orders;
import tn.esprit.spring.AhmedGuedri.entities.Panier;
import tn.esprit.spring.AhmedGuedri.entities.Payment;

import java.util.List;

public interface IOrderService {
    List<Orders> retrieveAllOrders();

    public Orders updateOrders(Orders orders);

    void deleteOrders(Long id);

    Orders addOrders (Orders orders ,Long iduser);

    Orders retrieveOrders(Long id);

    Orders addPanierToOrder(Long panierId, Long orderId);
    public Float TotalOrdersTVA (Long orderId);

}
