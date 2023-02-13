package tn.esprit.spring.AhmedGuedri.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.AhmedGuedri.entities.FamilleActe;
import tn.esprit.spring.AhmedGuedri.entities.Pathologie;

public interface FamilleActeRepository extends CrudRepository<FamilleActe, Long> {
}
