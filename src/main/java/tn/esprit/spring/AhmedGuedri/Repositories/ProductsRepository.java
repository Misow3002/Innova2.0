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

   /* @Query("SELECT DISTINCT p FROM Products p JOIN p.Product_order o WHERE Orders.BroughtDate BETWEEN :startDate AND :endDate AND :supplierId MEMBER OF p.userProducts")
    List<Products> findProductsSoldBySupplierLastMonth(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("supplierId") Long supplierId);*/
}