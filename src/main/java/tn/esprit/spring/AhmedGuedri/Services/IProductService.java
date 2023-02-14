package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.List;

public interface IProductService {
    List<Products> retrieveAllProducts();
    Products addOrUpdateProduct(Products p);
    Products getProduct(Integer IdProducts);
    void removeProduct(Integer IdProducts);
}
