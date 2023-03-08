package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.AhmedGuedri.Services.IPWDService;
import tn.esprit.spring.AhmedGuedri.Services.IUserService;
import tn.esprit.spring.AhmedGuedri.entities.HashedPWD;
import tn.esprit.spring.AhmedGuedri.entities.User;
import tn.esprit.spring.AhmedGuedri.payload.request.NewPasswordRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
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
    @PreAuthorize("hasRole('USER') or hasRole('PROVIDER') or hasRole('ADMIN') or hasRole('DELIVERY')")
    public String updateUser(HttpServletRequest request,@RequestBody User u) {
        String email= iUserService.UserVerificationReturnEmail(request);
        if (email.equals("Token Doesn't Match Authenfied User"))
            return "Token Doesn't Match Authenfied User";

        if (u.getEmail().equals(email))
        return iUserService.updateUser(u);
        else
            return "Email Doesn't Match Authenfied User";
    }



    @DeleteMapping("/deleteUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(HttpServletRequest request ,@RequestParam String emailAdress) {
        String email= iUserService.UserVerificationReturnEmail(request);
        if (email.equals("Token Doesn't Match Authenfied User"))
            return "Token Doesn't Match Authenfied User";
        return iUserService.deleteUser(emailAdress);
    }
    @PutMapping("/activateUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String ActivateUser(HttpServletRequest request, @RequestParam String emailAdress) {
        String email= iUserService.UserVerificationReturnEmail(request);
        if (email.equals("Token Doesn't Match Authenfied User"))
            return "Token Doesn't Match Authenfied User";
        return iUserService.ActivateUser(emailAdress);


    }
    //implementing the method RetievePasswordInfo
    @GetMapping("/RetievePasswordInfo")
    @PreAuthorize("hasRole('USER') or hasRole('PROVIDER') or hasRole('ADMIN') or hasRole('DELIVERY')")
    public String RetievePasswordInfo(HttpServletRequest request) {
        String email= iUserService.UserVerificationReturnEmail(request);
        if (email.equals("Token Doesn't Match Authenfied User"))
            return "Token Doesn't Match Authenfied User";
        return ipwdService.RetievePasswordInfo(email);

    }
    //Implementing VerifyUserToken

    @GetMapping("/confirm-account")
    @PreAuthorize("hasRole('USER') or hasRole('PROVIDER') or hasRole('ADMIN') or hasRole('DELIVERY')")
    public String VerifyUserToken(HttpServletRequest request, @RequestParam Long token) {
        String email= iUserService.UserVerificationReturnEmail(request);
        if (email.equals("Token Doesn't Match Authenfied User"))
            return "Token Doesn't Match Authenfied User";
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
    //Forgot Password
    @GetMapping("/recovery/ForgotPassword")
    public String ForgotPassword(@RequestParam String email,@RequestParam Boolean Phone,@RequestParam String PhoneNum) {

        return iUserService.ForgotPassword(email,Phone, PhoneNum);
    }
    //Verify Forgot Password Token
    @PostMapping("/recovery/VerifyForgotPasswordToken")
    public String VerifyForgotPasswordToken(@RequestParam String email, @RequestBody NewPasswordRequest newPasswordRequest, @RequestParam Long token) {
        //return "gg works2";
          return iUserService.VerifyForgotPasswordToken(email, newPasswordRequest.getPassword(), newPasswordRequest.getNewPassword(), token);
    }
// SendAndReceive


}
