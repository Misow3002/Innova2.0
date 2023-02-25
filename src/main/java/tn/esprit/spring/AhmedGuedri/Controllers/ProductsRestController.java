package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Services.IProductService;
import tn.esprit.spring.AhmedGuedri.entities.DetailedOrders;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductsRestController {
    @Autowired
    private IProductService productService;
    @PostMapping("/add")
    Products addStudent(@RequestBody Products products){
        return productService.addOrUpdateProduct(products);
    }
    @PostMapping("/update")
    Products updateStudent(@RequestBody Products products){
        return productService.addOrUpdateProduct(products);
    }
    @GetMapping("/get/{id}")
    Products getStudent(@PathVariable("id") Long IdProduct){
        return productService.getProduct(IdProduct);
    }
    @GetMapping("/all")
    List<Products> getAllStudent(){return productService.retrieveAllProducts();}
    @DeleteMapping("/delete/{id}")
    void deleteStudent(@PathVariable("id") Long IdProduct){
        productService.removeProduct(IdProduct);
    }


}
