package tn.esprit.spring.AhmedGuedri.Repositories;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.AhmedGuedri.entities.Products;
import tn.esprit.spring.AhmedGuedri.entities.ShoppingCart;
import tn.esprit.spring.AhmedGuedri.entities.User;
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

    ShoppingCart retrieveShoppingCartByUser(long parseLong);
    User retrieveUserById(long parseLong);
    Products retrieveProductById(long parseLong);
}
