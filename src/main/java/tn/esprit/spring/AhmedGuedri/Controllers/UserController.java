package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Services.IPWDService;
import tn.esprit.spring.AhmedGuedri.Services.IUserService;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping("/user/")
public class UserController {
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    private IUserService iUserService;
    private IPWDService ipwdService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("addUser")
    public ResponseEntity<User> addUser(@RequestBody User u,String Password) {

        if (!Password.isEmpty()) {
            iUserService.addUser(u);
            ipwdService.AssignPasswordToUser(u, passwordEncoder.encode(Password));
        }
        return new ResponseEntity<User>(HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User u,String Password) {

        return iUserService.updateUser(u);



        //if (Password.length()>7)
        //return ipwdService.EditPassword(u.,Password);




    }


    @DeleteMapping("/deleteUser")
    public ResponseEntity<User> deleteUser(@RequestParam String email) {
        iUserService.deleteUser(email);
        return new ResponseEntity<User>(HttpStatus.OK);

    }
    //implementing the method RetievePasswordInfo
    @GetMapping("/RetievePasswordInfo")
    public String RetievePasswordInfo(@RequestParam String email) {
        return ipwdService.RetievePasswordInfo(email);

    }
}
