package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.ShoppingCart;

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
    ShoppingCart retrieveShoppingCartByUser(String idUser);
    ShoppingCart createShoppingCartByUser(String idUser);
    



}