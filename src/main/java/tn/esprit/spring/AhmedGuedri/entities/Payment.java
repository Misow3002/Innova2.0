package tn.esprit.spring.AhmedGuedri.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idorder;

    //stripe
    int created;

    @Temporal(TemporalType.DATE)
    private Date datePayment;
    //String customerId;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
