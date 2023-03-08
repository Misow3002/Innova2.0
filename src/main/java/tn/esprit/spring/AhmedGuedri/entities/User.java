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

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long Id;
    private String FirstName;
    private String LastName;
    private Date BirthDate;
    @Enumerated(EnumType.STRING)
    private Roles Roles;
    private String Adress;
    private String Email;
    //Anti Bot + PWD Recover
    private Long Token;
    private Long PhoneNumber;
    private String Img_URL;
    private boolean Disabled;
    private String Country;
//Relation Messages
    @OneToMany()
    List<Message> SentList;
    @OneToMany()
    List<Message> ReceivedList;
    //Relation PWD
    @OneToOne
    HashedPWD hashedPWD;
    //Relation fees
    @OneToOne
    Fees fees;

    //Relation Inquiry
    @OneToMany(mappedBy = "userInquiries")
    List<Inquiry> InquiryList;
    //Relation Product
    @ManyToMany(mappedBy = "userProducts")
    List<Products> ProductList;
    //Relation Orders
    @OneToMany(cascade = CascadeType.ALL)
    List<Orders> User_orders;
    @OneToOne
    ShoppingCart shoppingCart;
    @OneToOne
    Delivery delivery;


}

