package tn.esprit.spring.AhmedGuedri.Services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.ShoppingCartRepository;
import tn.esprit.spring.AhmedGuedri.entities.ShoppingCart;
@Service

public class ShoppingCartServiceImpl implements IShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Override
    public List<ShoppingCart> retrieveAllShoppingCarts() {
        List<ShoppingCart> shoppingCarts = (List<ShoppingCart>) shoppingCartRepository.findAll();
        return shoppingCarts;
    }
    @Override
    public ShoppingCart addShoppingCart(ShoppingCart s) {
        return shoppingCartRepository.save(s);
    }
    @Override
    public void deleteShoppingCart(String id) {
        shoppingCartRepository.deleteById(Long.parseLong(id));
    }
    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart s) {
        return shoppingCartRepository.save(s);
    }
    @Override
    public ShoppingCart retrieveShoppingCart(String id) {
        return shoppingCartRepository.findById(Long.parseLong(id)).get();
    }
    //clear products from shopping cart
    @Override
    public void clearShoppingCart(String id) {
        ShoppingCart shoppingCart = retrieveShoppingCart(id);
        shoppingCart.setProductsList(null);
        updateShoppingCart(shoppingCart);
    }
//remove products from shopping cart when product stock is 0
    @Override
    public void removeProductFromShoppingCartEmpty(String id) {
        ShoppingCart shoppingCart = retrieveShoppingCart(id);
        shoppingCart.getProductsList().removeIf(p -> p.getNumberOfStock() == 0);
        updateShoppingCart(shoppingCart);
    }
//remove a product from shopping cart
    @Override
    public void removeProductFromShoppingCart(String id, String idProduct) {
        ShoppingCart shoppingCart = retrieveShoppingCart(id);
        shoppingCart.getProductsList().removeIf(p -> p.getIdProducts() == Long.parseLong(idProduct));
        updateShoppingCart(shoppingCart);
    }

//get shopping cart by user
    @Override
    public ShoppingCart retrieveShoppingCartByUser(String idUser) {
        return shoppingCartRepository.retrieveShoppingCartByUser(Long.parseLong(idUser));
    }
//create shopping cart by user
    @Override
    public ShoppingCart createShoppingCartByUser(String idUser) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(shoppingCartRepository.retrieveUserById(Long.parseLong(idUser)));
        return shoppingCartRepository.save(shoppingCart);
    }
//add product to shopping cart
    @Override
    public void addProductToShoppingCart(String id, String idProduct) {
        ShoppingCart shoppingCart = retrieveShoppingCart(id);
        shoppingCart.getProductsList().add(shoppingCartRepository.retrieveProductById(Long.parseLong(idProduct)));
        updateShoppingCart(shoppingCart);
    }
//get total price of shopping cart
    @Override
    public float getTotalPriceShoppingCart(String id) {
        ShoppingCart shoppingCart = retrieveShoppingCart(id);
        float totalPrice = 0;
        for (int i = 0; i < shoppingCart.getProductsList().size(); i++) {
            totalPrice += shoppingCart.getProductsList().get(i).getPrice();
        }
        return totalPrice;
    }


}




