package tn.esprit.spring.AhmedGuedri.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idorders")
    private Long IdOrders;
    @Enumerated(EnumType.STRING)
    private Payment_method paymentMethod;
    private String adress;
    @Temporal(TemporalType.TIMESTAMP)
    private Date BroughtDate;

    private float tax;
    private float total;

    //relation avec user
    @ManyToOne
    User user;


    //Relation -->Invoices
    @OneToOne
    Invoices OrdersInvoice;


    //Delivery
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Panier_id")
    Panier panier;

    @OneToOne
    Payment payment;



    //Relation -->Invoices
    //@OneToOne
   // Invoices OrdersInvoice;


    //Delivery
    @ManyToOne
    Delivery delivery;


    //Relation Payment
    @Override
    public String toString() {
        return  "IdOrders=" + IdOrders +"\n"+
                "paymentMethod"+paymentMethod+"\n"+
                "BroughtDate" + BroughtDate+"\n"+

                "tax=" + tax +"\n"+
                "total"+total+"\n";
    }

}

