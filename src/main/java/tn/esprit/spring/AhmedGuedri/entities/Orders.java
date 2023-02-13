package tn.esprit.spring.AhmedGuedri.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    private OrdersType StatusOrders;
    @Temporal(TemporalType.TIMESTAMP)
    private Date BroughtDate;
    private boolean Confirmation;
    //Delivery
    @ManyToOne
    Delivery delivery;

    //Relation Payment
    @OneToOne
    Payement payment;
    //Relation -->Invoices
    @OneToOne
    Invoices OrdersInvoice;
}

