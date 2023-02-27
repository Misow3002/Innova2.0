package tn.esprit.spring.AhmedGuedri.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
<<<<<<< HEAD
import java.util.HashSet;
import java.util.List;
import java.util.Set;

=======
>>>>>>> 096238298958478fc361a260c45ae5c7c3cd8c2c
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
<<<<<<< HEAD
    private double totalPrice;
    //relation one to one avec order
    @ManyToMany
    private Set<Products> products;


=======

    //relation one to one avec order
    @OneToOne
    private Orders orders;
>>>>>>> 096238298958478fc361a260c45ae5c7c3cd8c2c




}
