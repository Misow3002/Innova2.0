package tn.esprit.spring.AhmedGuedri.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class   Products implements Serializable {
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

    //Relation avec panier
    @ManyToOne(cascade = CascadeType.ALL)
    Panier panier;

    //Relation --> Inquiry
    @ManyToMany(mappedBy = "ProductList")
    List<Inquiry> InquiryList;
    //Relation --> USER
    @ManyToMany
    List<User> userProducts;
    //Relation -->Currency
    @ManyToMany
    List<Currency> CurrencyList;
    //Relation --> Tax

    @OneToMany(mappedBy = "feedbacksProd")
    List<Feedbacks> FeedbackList;


}

