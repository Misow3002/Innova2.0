package tn.esprit.spring.AhmedGuedri.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idorders")
    private Long IdOrders;
    @Enumerated(EnumType.STRING)
    private Order_Status orderStatus;
    @Enumerated(EnumType.STRING)
    private Payment_method paymentMethod;
    private String adress;
    @Temporal(TemporalType.TIMESTAMP)
    private Date BroughtDate;
    private float product_cost;
    private float tax;
    private float total;

    //relation avec user
    @ManyToOne
    User user;
<<<<<<< HEAD

    //Relation -->Invoices
    @OneToOne
    Invoices OrdersInvoice;


    //Delivery
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Panier_id")
    Panier panier;

    @OneToOne
    Payment payment;

=======

    //Relation -->Invoices
    @OneToOne
    Invoices OrdersInvoice;


    //Delivery
    @ManyToOne
    Delivery delivery;

>>>>>>> 096238298958478fc361a260c45ae5c7c3cd8c2c
    //Relation Payment


}

