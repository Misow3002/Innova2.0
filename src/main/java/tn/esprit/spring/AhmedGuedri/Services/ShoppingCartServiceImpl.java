package tn.esprit.spring.AhmedGuedri.Services;
import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.AhmedGuedri.Repositories.ProductsRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.ShoppingCartRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.ShoppingCart;
import tn.esprit.spring.AhmedGuedri.entities.User;
@Service

public class ShoppingCartServiceImpl implements IShoppingCartService {
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    UserRepository userRepository;
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


//add product to shopping cart
    @Override
    public void addProductToShoppingCart(String id, String idProduct) {
        ShoppingCart shoppingCart = retrieveShoppingCart(id);
        shoppingCart.getProductsList().add(productsRepository.findById(Long.parseLong(idProduct)).get());
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
  //create a shopping cart for every user created
    @Override
    public void createShoppingCartForUser(String id) {
        User u = userRepository.findById(Long.parseLong(id)).get();
        if (u.getShoppingCart() == null) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(u);
            addShoppingCart(shoppingCart);
            //EL MODIFICATION
            u.setShoppingCart(shoppingCart);
            userRepository.save(u);
        }
    }
    //create a shopping cart for all users
    @Scheduled(cron = "0 0 0 * * ?")
    @AfterReturning("execution(* tn.esprit.spring.AhmedGuedri.Services.UserServiceImpl.addUser(..))")
    @Override
    public void createShoppingCartForAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            createShoppingCartForUser(String.valueOf(users.get(i).getId()));
        }
    }




}




