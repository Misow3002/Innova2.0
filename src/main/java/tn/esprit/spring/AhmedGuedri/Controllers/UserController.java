package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.AhmedGuedri.Services.IPWDService;
import tn.esprit.spring.AhmedGuedri.Services.IUserService;
import tn.esprit.spring.AhmedGuedri.entities.HashedPWD;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user/")
public class UserController {
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
    private IUserService iUserService;
    private IPWDService ipwdService;
//    @Autowired
  //  private PasswordEncoder passwordEncoder;
    @PostMapping("addUser")
    public ResponseEntity<User> addUser(@RequestBody User User) {


            iUserService.addUser(User);
        System.out.println( User.getHashedPWD().getPassword());
            ipwdService.AssignPasswordToUser(User, User.getHashedPWD().getPassword());

        return new ResponseEntity<User>(HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User u) {
        return iUserService.updateUser(u);
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
    //Implementing VerifyUserToken
    @GetMapping("/VerifyUserToken")
    public String VerifyUserToken(@RequestParam String email, @RequestParam Long token) {
        return iUserService.VerifyUserToken(email, token);

    }
    //Implementing TopTierSellers
    @GetMapping("/TopTierSellers")
    public List<String> TopTierSellers() {
        return iUserService.TopTierSellers();

    }
    //Implementing Authenticate
    @GetMapping("/Authenticate")
    public void Authenticate(@RequestParam String email) {
        iUserService.Authenticate(email);

    }

}
