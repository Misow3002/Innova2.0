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
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//mahdi
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
    private Number PhoneNumber;
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
    //Relation Fees
    @OneToMany(mappedBy = "userFees")
    List<Fees> FeesList;
    //Relation Inquiry
    @OneToMany(mappedBy = "userInquiries")
    List<Inquiry> InquiryList;
    //Relation Product
    @ManyToMany(mappedBy = "userProducts")
    List<Products> ProductList;

    //relation avec order
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Orders> orders;


}

