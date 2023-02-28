package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Services.IPWDService;
import tn.esprit.spring.AhmedGuedri.Services.IUserService;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping("/password/")
public class HashedPWDController {
    private IUserService iUserService;
    private IPWDService ipwdService;


    @PutMapping("/edit/")
    public String updateUser(String Email, String PrevPassword, String NewPassword) {

        return ipwdService.EditPassword(Email, PrevPassword, NewPassword);


    }
}