package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    @GetMapping("/order/get/{dates}/{datee}/{id}")
    List<DetailedOrders> getDetailedOrdersbyDaterange(@PathVariable("dates")@DateTimeFormat(pattern="yyyy-MM-dd") Date dates, @PathVariable("datee")@DateTimeFormat(pattern="yyyy-MM-dd") Date datee, @PathVariable("id") Long id){
        return productService.getDetailedOrdersbyDaterange(dates,datee,id);
    }
    @GetMapping("/order/getstats/{dates}/{datee}/{id}")
    String getDetailedOrdersbyDaterangeStats(@PathVariable("dates")@DateTimeFormat(pattern="yyyy-MM-dd") Date dates, @PathVariable("datee")@DateTimeFormat(pattern="yyyy-MM-dd") Date datee, @PathVariable("id") Long id){
        return productService.getStatisticsbyDaterange(dates,datee,id);
    }
    @GetMapping("/order/getprods/{id}")
    String getprodsandstockbysup(@PathVariable("id") Long id){
        return productService.getnumberofordersbyeveryproductforasupplier(id);
    }
}
