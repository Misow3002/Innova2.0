package tn.esprit.spring.AhmedGuedri.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.AhmedGuedri.entities.Pathologie;
import tn.esprit.spring.AhmedGuedri.entities.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {
}
