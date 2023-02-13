package tn.esprit.spring.AhmedGuedri.entities;


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
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPatient")
    private Long idPatient;
    @Enumerated(EnumType.STRING)
    private TypePieceIdentie typepieceidentie;
    private String identifiantPieceIdentie;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEmission;
    private String nomP;
    private String prenomP;
    @ManyToMany
    Set<Pathologie> pathologie;
}