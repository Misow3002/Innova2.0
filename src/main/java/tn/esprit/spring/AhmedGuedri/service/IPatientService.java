package tn.esprit.spring.AhmedGuedri.service;

import tn.esprit.spring.AhmedGuedri.entities.Patient;

public interface IPatientService {
    public Patient ajouterPatientEtAffecterAPathologie(Patient p,String codePath);

}
