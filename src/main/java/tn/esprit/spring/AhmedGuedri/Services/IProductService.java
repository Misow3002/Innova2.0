package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.DetailedOrders;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.Date;
import java.util.List;

public interface IProductService {
    List<Products> retrieveAllProducts();
    Products addOrUpdateProduct(Products p);
    Products getProduct(Long IdProducts);
    void removeProduct(Long IdProducts);
    void addOrUpdatedetailedOrders();

    List<DetailedOrders> getDetailedOrdersbyDaterange(Date startdate, Date enddate, Long supplier);
}
