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
public class Inquiry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idinquiry")
    private Long IdInquiry;
    private String Description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreateDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ExpireDate;
    private boolean Status;
    //Relation-->User
    @ManyToOne
    User userInquiries;
    //Relation-->Product
    @ManyToMany
    List<Products> ProductList;

}

