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
@RequestMapping("/user/")
public class UserController {
    private IUserService iUserService;
    private IPWDService ipwdService;
    @PostMapping("addUser")
    public ResponseEntity<User> addUser(@RequestBody User u,String Password) {

        if (!Password.isEmpty()) {
            iUserService.addUser(u);
            ipwdService.AssignPasswordToUser(u, Password);
        }
        return new ResponseEntity<User>(HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User u,String Password) {

         iUserService.updateUser(u);



        if (Password.length()>7)
        ipwdService.AssignPasswordToUser(u,Password);
        return new ResponseEntity<User>(HttpStatus.CREATED);

    }


    @DeleteMapping("/deleteUser")
    public ResponseEntity<User> deleteUser(@RequestBody User u) {
        iUserService.deleteUser(u);
        return new ResponseEntity<User>(HttpStatus.ACCEPTED);

    }
    //implementing the method RetievePasswordInfo
    @GetMapping("/RetievePasswordInfo")
    public ResponseEntity<String> RetievePasswordInfo(@RequestBody User u) {
        return new ResponseEntity<String>(ipwdService.RetievePasswordInfo(u),HttpStatus.OK);

    }
}
