package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.ProductsRepository;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService{
    @Autowired
    ProductsRepository productsRepository;
    @Override
    public List<Products> retrieveAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Products addOrUpdateProduct(Products p) {
        return productsRepository.save(p);
    }

    @Override
    public Products getProduct(Integer IdProducts) {
        return productsRepository.findById(IdProducts).orElse(null);
    }

    @Override
    public void removeProduct(Integer IdProducts) {
        productsRepository.deleteById(IdProducts);
    }
}
