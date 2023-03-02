package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.ShoppingCart;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;

public interface IShoppingCartService {
    List<ShoppingCart> retrieveAllShoppingCarts();
    ShoppingCart addShoppingCart(ShoppingCart s);
    void deleteShoppingCart(String id);
    ShoppingCart updateShoppingCart(ShoppingCart s);
    ShoppingCart retrieveShoppingCart(String id);
    void clearShoppingCart(String id);
    void removeProductFromShoppingCartEmpty(String id);
    void removeProductFromShoppingCart(String id, String idProduct);
    void addProductToShoppingCart(String id, String idProduct);
    float getTotalPriceShoppingCart(String id);
    void createShoppingCartForUser( String idUser);
    



}