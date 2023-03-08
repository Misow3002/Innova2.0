package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;
import tn.esprit.spring.AhmedGuedri.entities.ProductCategory;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

}