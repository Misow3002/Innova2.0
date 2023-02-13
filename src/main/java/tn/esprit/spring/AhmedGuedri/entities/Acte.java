package tn.esprit.spring.AhmedGuedri.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Acte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idActe")
    private Long idActe;
    private String codeActe;
    private int CotationActe;
    private float prixUnitaireActe;
    private String designationActe;

    @ManyToOne
    FamilleActe familleActe;

    @ManyToMany(mappedBy ="acte",cascade = CascadeType.PERSIST)
    Set<Pathologie> pathologie;
}