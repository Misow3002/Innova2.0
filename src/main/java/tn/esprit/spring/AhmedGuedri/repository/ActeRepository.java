package tn.esprit.spring.AhmedGuedri.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.AhmedGuedri.entities.Acte;
import tn.esprit.spring.AhmedGuedri.entities.FamilleActe;

public interface ActeRepository extends CrudRepository<Acte, Long> {
}
