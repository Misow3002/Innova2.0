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
public class Delivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iddelivery")
    private Long IdDelivery;
    @Temporal(TemporalType.DATE)
    private Date StarDate ;
    @Temporal(TemporalType.DATE)
    private Date EndDate ;
<<<<<<< HEAD
=======

>>>>>>> 096238298958478fc361a260c45ae5c7c3cd8c2c


}

