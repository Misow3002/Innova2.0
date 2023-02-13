package tn.esprit.spring.AhmedGuedri.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.entities.Pathologie;
import tn.esprit.spring.AhmedGuedri.repository.PathologieRepository;


@Service
@AllArgsConstructor
public class PathologieService implements IPathologieService{
    PathologieRepository pathologieRepository;

    @Override
    public Pathologie ajouterPathologie(Pathologie path) {
        return pathologieRepository.save(path);
    }
}
