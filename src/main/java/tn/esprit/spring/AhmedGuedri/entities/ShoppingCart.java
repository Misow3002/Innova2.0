//implement entity class ShoppingCart
package tn.esprit.spring.AhmedGuedri.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Cart_Id;
   //relation Product
    @ManyToMany
    private List<Products> productsList;
    //Relation User
    @OneToOne
    private User user;
    



}