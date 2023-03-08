package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Repositories.ProductsRepository;
import tn.esprit.spring.AhmedGuedri.Services.IProductService;
import tn.esprit.spring.AhmedGuedri.entities.DetailedOrders;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductsRestController {
    @Autowired
    private IProductService productService;
    private ProductsRepository productsRepository;
    @PostMapping("/add")
    Products addProduct(@RequestBody Products products){
        return productService.addOrUpdateProduct(products);
    }
    @PostMapping("/update")
    Products updateProduct(@RequestBody Products products){
        return productService.addOrUpdateProduct(products);
    }
    @GetMapping("/get/{id}")
    Products getProduct(@PathVariable("id") Long IdProduct){
        return productService.getProduct(IdProduct);
    }
    @GetMapping("/all")
    List<Products> getAllProducts(){System.out.println(productService.retrieveAllProducts());
        return productService.retrieveAllProducts();
        }
    /*@GetMapping("/allsorted")
    List<Products> getAllProductsbyorders(){return productService.getProductsSortedByNumOrders();}*/
    @DeleteMapping("/delete/{id}")
    void deleteProducts(@PathVariable("id") Long IdProduct){
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
    /*@GetMapping("/sortByOrders")
    public List<Products> getProductsSortedByOrders() {
        List<Products> productsList = productService.retrieveAllProducts();
        System.out.println(productsList);
        //Collections.sort(productsList, (p1, p2) ->
          //      Integer.compare(p2.getProduct_order().size(), p1.getProduct_order().size()));
        return productsList;
    }
    @GetMapping("/sortedByNumOrders")
    public List<Products> getProductsSortedByNumOrders() {
        List<Object[]> productsWithNumOrders = productsRepository.findAllSortedByNumOrders();
        List<Products> sortedProducts = new ArrayList<>();

        for (Object[] productWithNumOrders : productsWithNumOrders) {
            Products product = (Products) productWithNumOrders[0];
            sortedProducts.add(product);
        }

        return sortedProducts;
    }*/
    @GetMapping("/sortedByNumOrders2")
    public List<Products> getAllProductsSortedByOrderCount() {
        List<Products> products = productsRepository.findAll();
        products.sort(Comparator.comparingInt(p -> p.getProduct_order().size()));
        return products;
    }
}
