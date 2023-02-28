package tn.esprit.spring.AhmedGuedri.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class HashedPWD implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long PassId;
    //RISK 90%
    private String Password;
    @Temporal(TemporalType.DATE)
    private Date ChangeDate;
    @OneToOne
    User user;

}

