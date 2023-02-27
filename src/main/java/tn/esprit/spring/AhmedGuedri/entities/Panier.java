package tn.esprit.spring.AhmedGuedri.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Panier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPanier")
    private Long IdPanier;
    private long quantity;
    private double totalPrice;
    //relation one to one avec order
    @ManyToMany
    private Set<Products> products;






}
