package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.Orders;
import tn.esprit.spring.AhmedGuedri.entities.Products;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    @Query("select p from Products p")
    List<Products>findAll();
    @Query("SELECT p FROM Products p ORDER BY size(p.Product_order)")
    List<Products> findAllOrderByNumberOfOrders();

    @Query("SELECT p, COUNT(o) as num_orders FROM Products p JOIN p.Product_order o GROUP BY p ORDER BY num_orders DESC")
    List<Object[]> findProductsOrderByNumOrders();

    @Query("SELECT p, COUNT(o) AS numOrders " +
            "FROM Products p " +
            "JOIN p.Product_order o " +
            "GROUP BY p " +
            "ORDER BY numOrders DESC")
    List<Object[]> findAllSortedByNumOrders();
}