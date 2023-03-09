package tn.esprit.spring.AhmedGuedri.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Enumerated(EnumType.STRING)
    private OrdersType StatusOrders;
    @Temporal(TemporalType.TIMESTAMP)
    private Date BroughtDate;
    private boolean Confirmation;
    //for delivery usage
    private String shippingAdresse;
    //Delivery
    @ManyToOne
    @JsonIgnore
    Delivery delivery;

    //Relation Payment
    @OneToOne
    Payement payment;
    //Relation -->Invoices
    @OneToOne
    Invoices OrdersInvoice;
    //Relation-->ShoppingCart
    @OneToOne
    ShoppingCart shoppingCart;
    //Relation Product
    @ManyToMany
    private List<Products> productsList;


}

