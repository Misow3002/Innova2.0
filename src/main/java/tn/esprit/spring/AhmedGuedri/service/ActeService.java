package tn.esprit.spring.AhmedGuedri.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.entities.Acte;
import tn.esprit.spring.AhmedGuedri.entities.Pathologie;
import tn.esprit.spring.AhmedGuedri.repository.ActeRepository;
import tn.esprit.spring.AhmedGuedri.repository.PathologieRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class ActeService implements IActeService{
    PathologieRepository pathologieRepository;
    ActeRepository acteRepository;


    @Override
    public void affecterActeAPathologie(String codeActe, String codePathologie) {
        Pathologie pathologie= pathologieRepository.findById(Long.valueOf(codePathologie)).get();

    }
    @Scheduled(cron = "*/30 * * * * *")
    @Override
    public void calculNommreActeParPathologie() {

            List<Acte> listActe = (List<Acte>) acteRepository.findAll();

            listActe.forEach(acte -> {log.info("nbr=");
            });

        }
    }
