package tn.esprit.spring.AhmedGuedri.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.entities.Pathologie;
import tn.esprit.spring.AhmedGuedri.entities.Patient;
import tn.esprit.spring.AhmedGuedri.repository.PathologieRepository;
import tn.esprit.spring.AhmedGuedri.repository.PatientRepository;

import java.util.Set;

@Slf4j
@Service

public class PatientService implements IPatientService {

@Autowired
PatientRepository patientRepository;
@Autowired
PathologieRepository pathologieRepository;
    private Patient patient;

    @Override
    public Patient ajouterPatientEtAffecterAPathologie(Patient p, String codePath) {
        Pathologie pathologie = pathologieRepository.findById(Long.valueOf(codePath)).get();
        patient.setPathologie((Set<Pathologie>) pathologie);
        patientRepository.save(patient);
        return patient;
    }
}
