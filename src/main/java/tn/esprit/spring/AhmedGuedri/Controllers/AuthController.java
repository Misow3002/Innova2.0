package tn.esprit.spring.AhmedGuedri.Controllers;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.context.WebApplicationContext;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.Services.IEmailService;
import tn.esprit.spring.AhmedGuedri.Services.IPWDService;
import tn.esprit.spring.AhmedGuedri.Services.IUserService;
import tn.esprit.spring.AhmedGuedri.Services.PWDService;
import tn.esprit.spring.AhmedGuedri.entities.HashedPWD;
import tn.esprit.spring.AhmedGuedri.entities.Role;
import tn.esprit.spring.AhmedGuedri.entities.RolesTypes;
import tn.esprit.spring.AhmedGuedri.entities.User;
import tn.esprit.spring.AhmedGuedri.payload.mailing.EmailDetails;
import tn.esprit.spring.AhmedGuedri.payload.request.LoginRequest;
import tn.esprit.spring.AhmedGuedri.payload.request.SignupRequest;
import tn.esprit.spring.AhmedGuedri.payload.response.JwtResponse;
import tn.esprit.spring.AhmedGuedri.payload.response.MessageResponse;
import tn.esprit.spring.AhmedGuedri.security.jwt.JwtUtils;
import tn.esprit.spring.AhmedGuedri.security.services.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  AuthenticationManager authenticationManager;

 private IEmailService emailService;
  UserRepository userRepository;


  private IUserService iUserService;

  private IPWDService ipwdService;


  PasswordEncoder encoder;

  private WebApplicationContext appContext;
  JwtUtils jwtUtils;


  private PasswordEncoder passwordEncoder;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody User User) {


    if (userRepository.existsByEmail(User.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }
    iUserService.addUser(User);
    //Create EmailDetails
    EmailDetails emailDetails = new EmailDetails();
    emailDetails.setRecipient(User.getEmail());
    emailDetails.setSubject("Account Confirmation");
    emailDetails.setMsgBody("Hello "+User.getFirstName()+" "+User.getLastName()+" ,\n\n" +
            " welcome to our platform , your account has been created successfully .\n\n" +
            " please confirm your account by clicking on the following link : http://localhost:8083/user/confirm-account?token="+User.getToken()+"\n\n" +
            " or Enter Code Manually : "+User.getToken()+"\n\nBest Regards,\n" );

    String status
            = emailService.sendSimpleMail(emailDetails);



    return ResponseEntity.ok(new MessageResponse("User registered successfully !"+" // "+status));
  }
}
