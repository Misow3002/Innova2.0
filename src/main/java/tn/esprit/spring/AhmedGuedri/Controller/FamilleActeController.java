package tn.esprit.spring.AhmedGuedri.Controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.AhmedGuedri.entities.FamilleActe;
import tn.esprit.spring.AhmedGuedri.entities.Pathologie;
import tn.esprit.spring.AhmedGuedri.service.IFamilleActeService;
import tn.esprit.spring.AhmedGuedri.service.IPathologieService;

@AllArgsConstructor
@RestController
@RequestMapping("/familleacte/")
public class FamilleActeController {

    private IFamilleActeService iFamilleActeService;

    @PostMapping("addfamilleacte")
    public ResponseEntity<FamilleActe> ajouterphatologie(@RequestBody FamilleActe ce) {

        System.out.printf(String.valueOf(ce));

        iFamilleActeService.ajouterFamilleActeEtActAssocie(ce);

        return new ResponseEntity<FamilleActe>(HttpStatus.CREATED);

    }
}
