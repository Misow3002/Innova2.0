package tn.esprit.spring.AhmedGuedri.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Services.IPWDService;
import tn.esprit.spring.AhmedGuedri.Services.IUserService;
import tn.esprit.spring.AhmedGuedri.entities.User;
import tn.esprit.spring.AhmedGuedri.payload.request.NewPasswordRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping("/password/")
public class HashedPWDController {
    private IUserService iUserService;
    private IPWDService ipwdService;
    private PasswordEncoder passwordEncoder;


    @PutMapping("/edit/")
    @PreAuthorize("hasRole('USER') or hasRole('PROVIDER') or hasRole('ADMIN')")
    public String updateUser(HttpServletRequest request, @RequestBody NewPasswordRequest newPasswordRequest) {
        String email= iUserService.UserVerificationReturnEmail(request);
        if (email.equals("Token Doesn't Match Authenfied User"))
            return "Token Doesn't Match Authenfied User";
        return ipwdService.EditPassword(email, passwordEncoder.encode(newPasswordRequest.getPassword()), newPasswordRequest.getNewPassword());


    }
}