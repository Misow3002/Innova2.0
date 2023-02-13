package tn.esprit.spring.AhmedGuedri.Controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.entities.Pathologie;
import tn.esprit.spring.AhmedGuedri.entities.Patient;
import tn.esprit.spring.AhmedGuedri.service.PathologieService;
import tn.esprit.spring.AhmedGuedri.service.PatientService;

@AllArgsConstructor
@RestController
@RequestMapping("/patient/")
public class PatientController {
@Autowired
    PatientService patientService;
@Autowired
    PathologieService pathologieService;

    @PostMapping("/ajouterPatientEtAffecterAPathologie/{codePath}")
    public void ajouterPatientEtAffecterAPathologie(@RequestBody Pathologie pathologie,
                                                    @PathVariable("codePath") String codePath) {

        //patientService.ajouterPatientEtAffecterAPathologie(p,codePath);
    }
}
