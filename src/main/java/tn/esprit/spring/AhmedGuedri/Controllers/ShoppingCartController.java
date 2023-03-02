//create shopping cart controller
package tn.esprit.spring.AhmedGuedri.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.AhmedGuedri.Services.IShoppingCartService;
import tn.esprit.spring.AhmedGuedri.entities.ShoppingCart;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    IShoppingCartService shoppingCartService;
    @GetMapping("/retrieve-all-shoppingCarts")
    public List<ShoppingCart> getShoppingCarts() {
        List<ShoppingCart> list = shoppingCartService.retrieveAllShoppingCarts();
        return list;
    }
    @PostMapping("/add-shoppingCart")
    public ShoppingCart addShoppingCart(@RequestBody ShoppingCart s) {
        ShoppingCart shoppingCart = shoppingCartService.addShoppingCart(s);
        return shoppingCart;
    }
    @DeleteMapping("/remove-shoppingCart/{shoppingCart-id}")
    public void removeShoppingCart(@PathVariable("shoppingCart-id") String shoppingCartId) {
        shoppingCartService.deleteShoppingCart(shoppingCartId);
    }
    @PutMapping("/modify-shoppingCart")
    public ShoppingCart modifyShoppingCart(@RequestBody ShoppingCart s) {
        return shoppingCartService.updateShoppingCart(s);
    }
    @GetMapping("/retrieve-shoppingCart/{shoppingCart-id}")
    public ShoppingCart retrieveShoppingCart(@PathVariable("shoppingCart-id") String shoppingCartId) {
        return shoppingCartService.retrieveShoppingCart(shoppingCartId);
    }
    @PutMapping("/clear-shoppingCart/{shoppingCart-id}")
    public void clearShoppingCart(@PathVariable("shoppingCart-id") String shoppingCartId) {
        shoppingCartService.clearShoppingCart(shoppingCartId);
    }
    @PutMapping("/remove-product-from-shoppingCart-empty/{shoppingCart-id}")
    public void removeProductFromShoppingCartEmpty(@PathVariable("shoppingCart-id") String shoppingCartId) {
        shoppingCartService.removeProductFromShoppingCartEmpty(shoppingCartId);
    }
    @PutMapping("/remove-product-from-shoppingCart/{shoppingCart-id}/{product-id}")
    public void removeProductFromShoppingCart(@PathVariable("shoppingCart-id") String shoppingCartId,
            @PathVariable("product-id") String productId) {
        shoppingCartService.removeProductFromShoppingCart(shoppingCartId, productId);
    }
    @PutMapping("/add-product-to-shoppingCart/{shoppingCart-id}/{product-id}")
    public void addProductToShoppingCart(@PathVariable("shoppingCart-id") String shoppingCartId,
            @PathVariable("product-id") String productId) {
        shoppingCartService.addProductToShoppingCart(shoppingCartId, productId);
    }
    @PutMapping("/create-shoppingCart-for-user/{user-id}")
    public void createShoppingCartForUser(@PathVariable("user-id") String userId) {
        shoppingCartService.createShoppingCartForUser(userId);
    }
    

    

 






}