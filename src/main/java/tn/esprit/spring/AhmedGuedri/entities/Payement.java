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
public class Payement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpayment")
    private Long IdPayment;
    private PaymentType PaymentType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date PaymentDate;
    private boolean ConfirmationPayments;
    //Relation Orders
    @OneToOne (mappedBy = "payment")
    Orders orders;
    //Relation Invoices
    @OneToOne
    Invoices PaymentInvoice;
}

