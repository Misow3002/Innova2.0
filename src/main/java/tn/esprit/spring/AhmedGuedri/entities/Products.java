package tn.esprit.spring.AhmedGuedri.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idproducts")
    private Long IdProducts;
    private String NameProducts;
    private String Description;
    private Float Price;
    private String AdressProducts;
    private String RegionProducts;
    private boolean Available;
    private int NumberOfStock;
    //Relation --> Inquiry
    @ManyToMany(mappedBy = "ProductList")
    List<Inquiry> InquiryList;
    //Relation --> USER
    @ManyToMany(fetch = FetchType.LAZY)
    List<User> userProducts;
    //Relation -->Currency
    @ManyToMany
    List<Currency> CurrencyList;
    //Relation --> Tax
    @ManyToOne
    Tax tax;
    //Relation --> Feedbacks
    @OneToMany(mappedBy = "feedbacksProd")
    List<Feedbacks> FeedbackList;

    //Relation --> Orders
    @ManyToMany//(mappedBy = "productsList")
    List<Orders> Product_order;

    /*public List<List<Products>> getOrders() {
        System.out.println(Product_order.stream().map(Orders::getProductsList).collect(Collectors.toList()));
        return Product_order.stream().map(Orders::getProductsList).collect(Collectors.toList());

    }*/
}

