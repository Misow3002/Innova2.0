package tn.esprit.spring.AhmedGuedri.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String firstName;
    private String lastName;
    private Date BirthDate;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();
    private String Adress;
    private String email;
    //Anti Bot + PWD Recover
    private Long Token;
    private Long PhoneNumber;
    private String Img_URL;
    private boolean Disabled;
    private String Country;
//Relation Messages
    @ManyToMany()
    List<Message> SentList;
    @ManyToMany()
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

    @ManyToMany
    List<ChatRoom> chatRooms;
}

