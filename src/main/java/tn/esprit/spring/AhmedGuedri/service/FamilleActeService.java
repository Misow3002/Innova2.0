package tn.esprit.spring.AhmedGuedri.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.entities.Acte;
import tn.esprit.spring.AhmedGuedri.entities.FamilleActe;
import tn.esprit.spring.AhmedGuedri.repository.ActeRepository;
import tn.esprit.spring.AhmedGuedri.repository.FamilleActeRepository;

@Service
@AllArgsConstructor
public class FamilleActeService implements IFamilleActeService{

   FamilleActeRepository familleActeRepository;

    @Override
    public FamilleActe ajouterFamilleActeEtActAssocie(FamilleActe facte) {
      return familleActeRepository.save(facte);
    }
}
