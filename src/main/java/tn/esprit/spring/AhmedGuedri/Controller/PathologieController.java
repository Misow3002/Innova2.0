package tn.esprit.spring.AhmedGuedri.Controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.AhmedGuedri.entities.Pathologie;
import tn.esprit.spring.AhmedGuedri.entities.User;
import tn.esprit.spring.AhmedGuedri.service.IPathologieService;

@AllArgsConstructor
@RestController
@RequestMapping("/phatologie/")
public class PathologieController {

    private IPathologieService iPathologieService;

    @PostMapping("addphatologie")
    public ResponseEntity<Pathologie> ajouterphatologie(@RequestBody Pathologie ce) {

        System.out.printf(String.valueOf(ce));

        iPathologieService.ajouterPathologie(ce);

        return new ResponseEntity<Pathologie>(HttpStatus.CREATED);

    }
}
