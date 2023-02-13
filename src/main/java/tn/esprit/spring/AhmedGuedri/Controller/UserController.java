package tn.esprit.spring.AhmedGuedri.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.entities.User;
import tn.esprit.spring.AhmedGuedri.service.IUserService;

@AllArgsConstructor
@RestController
@RequestMapping("/user/")
public class UserController {
    private IUserService iUserService;
    @PostMapping("addUser")
    public ResponseEntity<User> ajouterUser(@RequestBody User ce) {

        System.out.printf(String.valueOf(ce));

        iUserService.ajouterUser(ce);

        return new ResponseEntity<User>(HttpStatus.CREATED);

    }


    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User e) {

        return iUserService.updateUser(e);

    }

    @PutMapping("/removeUser/{idUser}")
    public User removeUser(@PathVariable("idUser") Long idUser) {

        return iUserService.removeUser(idUser);

    }
}
