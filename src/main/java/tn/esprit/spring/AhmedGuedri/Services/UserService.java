package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tn.esprit.spring.AhmedGuedri.Repositories.FeedbacksRepository;

import tn.esprit.spring.AhmedGuedri.Repositories.RoleRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.HashedPWD;
import tn.esprit.spring.AhmedGuedri.entities.Role;
import tn.esprit.spring.AhmedGuedri.entities.RolesTypes;
import tn.esprit.spring.AhmedGuedri.entities.User;
import tn.esprit.spring.AhmedGuedri.payload.mailing.EmailDetails;
import tn.esprit.spring.AhmedGuedri.payload.response.JwtResponse;
import tn.esprit.spring.AhmedGuedri.security.jwt.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements IUserService{
    JwtUtils jwtUtils;
    UserRepository userRepository;
    private IEmailService emailService;
    FeedbacksRepository feedbacksRepository;

    RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void addUser(User u) {

        u.setToken(TokenGenerator(7));
        u.setJoined(new Date());
        u.setEnabled(true);
        System.out.println(u);
        Set<Role> strRoles = u.getRoles();
        System.out.println(strRoles);
        HashedPWD newUserPass = new HashedPWD();
        Date date = new Date();
        newUserPass.setPassword(passwordEncoder.encode(u.getHashedPWD().getPassword()));
        newUserPass.setChangeDate(date);
        newUserPass.setUser(u);
        u.setHashedPWD(newUserPass);
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role providerrRole = roleRepository.findByName(RolesTypes.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(providerrRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.getName().toString()) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(RolesTypes.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "ROLE_DELIVERY":
                        Role modRole = roleRepository.findByName(RolesTypes.ROLE_DELIVERY)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;

                    case "ROLE_PROVIDER":
                        Role providerRole = roleRepository.findByName(RolesTypes.ROLE_PROVIDER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(providerRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(RolesTypes.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        u.setRoles(roles);
         userRepository.save(u);
        System.out.println("User Created");
    }

    @Override
    public String updateUser(User u) {
        System.out.println(u);
        User u2=userRepository.findByEmailEquals(u.getEmail());
        //---
        //ADD REM
        Set<Role> roles = new HashSet<>(u.getRoles());
        Set<Role> strRoles = new HashSet<>();
        roles.forEach(role -> {
         //   System.out.println(role.getId().toString()+" "+role.getName().toString());
            switch (role.getName().toString()) {
                case "ROLE_ADMIN":
                    Role adminRole = roleRepository.findByName(RolesTypes.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    strRoles.add(adminRole);

                    break;
                case "ROLE_DELIVERY":
                    Role modRole = roleRepository.findByName(RolesTypes.ROLE_DELIVERY)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    strRoles.add(modRole);

                    break;

                case "ROLE_PROVIDER":
                    Role providerRole = roleRepository.findByName(RolesTypes.ROLE_PROVIDER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    strRoles.add(providerRole);
                    break;
                default:
                    Role userRole = roleRepository.findByName(RolesTypes.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    strRoles.add(userRole);
            }
        });
        //u2.setRoles(strRoles);
        //ADD REMM
        u2.setId(u2.getId());
//        //set the rest of the fields
        u2.setFirstName(u.getFirstName());
        u2.setLastName(u.getLastName());
        u2.setAdress(u.getAdress());
        u2.setBirthDate(u.getBirthDate());
        u2.setCountry(u.getCountry());

        u2.setPhoneNumber(u.getPhoneNumber());
        u2.setRoles(u.getRoles());
        u2.setHashedPWD(u2.getHashedPWD());
        u2.setEnabled(u.isEnabled());
        u2.setImg_URL(u2.getImg_URL());
        u2.setToken(u2.getToken());
        u2.setFees(u2.getFees());
        u2.setShoppingCart(u2.getShoppingCart());
        u2.setRoles(strRoles);
        userRepository.save(u2);



        return ("User Updated");
    }

    @Override
    public String deleteUser(String Email) {
   User u= userRepository.findByEmailEquals(Email);
   u.setEnabled(false);
    userRepository.save(u);
        return  "User Disabled";
    }
    //Activate User Account
    @Override
    public String ActivateUser(String Email) {
        User u= userRepository.findByEmailEquals(Email);
        u.setEnabled(true);
        userRepository.save(u);
       return "User Enabled";
    }

    public String VerifyUserToken(String Email,Long token){
        User u= userRepository.findByEmailEquals(Email);
        System.out.println("User Token : "+u.getToken());
        if(u.getToken().equals(token)){
            u.setToken(0L);
            userRepository.save(u);
           return ("User Account has been Verified");
        }
           return  "Wrong Token";
    }


    @Override
    public String ForgotPassword(String Email) {
        User u= userRepository.findByEmailEquals(Email);
        u.setToken(TokenGenerator(6));
        userRepository.save(u);

        //SENDS OTP TO USER EMAIL
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(u.getEmail());
        emailDetails.setSubject("Forgot Password");
        emailDetails.setMsgBody("Hello "+u.getFirstName()+" "+u.getLastName()+" ,\n\n" +
                "Your OTP Code is : "+u.getToken()+"\n\n" +
                "Regards,\n" +
                "Team E-Commerce");

        String status
                = emailService.sendSimpleMail(emailDetails);

        //END

        return status;
    }

    @Override
    public String VerifyForgotPasswordToken(String Email,String PrevPass,String NewPass ,Long token) {
        User u= userRepository.findByEmailEquals(Email);
        HashedPWD hashedPWD = u.getHashedPWD();
        if(u.getToken().equals(token) && PrevPass.equals(NewPass)){
            u.setToken(0L);
            hashedPWD.setPassword(passwordEncoder.encode(NewPass));
            userRepository.save(u);
            //GRANT PERMISSION TO CHANGE PASSWORD --> Redirect to Change Password Page
            //SENDS OTP TO USER EMAIL
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(u.getEmail());
            emailDetails.setSubject("Password Changed");
            emailDetails.setMsgBody("Hello "+u.getFirstName()+" "+u.getLastName()+" ,\n\n" +
                    "Your Password has been Changed Successfully\n\n" +
                    "Regards,\n" +
                    "Team E-Commerce");
            return "User Password Changed"  ;
        }
        return  "Passwords Doesn't Match or Wrong Token";
    }

    @Override
    public  List<String> TopTierSellers() {

   List<String> ST= feedbacksRepository.TopTierSellers();

//        for (User u : ST) {
//            System.out.println(u.getFirstName()+u.getLastName());
//        }
        return ST;
    }

    @Scheduled(cron = "0 * * * * 7")
    public void AntiBot() {
        Date datenow = new Date();

        userRepository.findAll().forEach(u->{
            if (u.getToken().toString().length()==7)
            {
            long diff = datenow.getTime() - u.getJoined().getTime();
            if( ((diff / (24 * 60 * 60 * 1000) <= 7))){
        u.setEnabled(false);
                userRepository.save(u);
            }
        }
        });

    }

    @Override
    public int NumberOfSubs() {
        return userRepository.NumberOfSubs();
    }

    @Override
    public void Authenticate(String Email) {
       // System.out.println("FETCHING USER");
        List<Object[]> user = userRepository.Authentification(Email);
        if (user.size() != 0)
        {
            User u =(User)user.get(0)[0];
            HashedPWD h =(HashedPWD) user.get(0)[1];
         //   System.out.println(u.getEmail() + " | " + h.getPassword());
        }
        else
        {
            System.out.println("User Not Found");
        }

    }

    @Override
    public Long TokenGenerator(int ends) {
        String token=new Random().nextLong()+"";
        token=token.substring(0,ends);
        //7 CHARACTERS FOR SIGN
        //6 FOR RECO
        return Math.abs(Long.parseLong(token));

    }

    @Override
    public String UserVerificationReturnEmail(HttpServletRequest request) {
        String headerAuth= request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return jwtUtils.getUserNameFromJwtToken(headerAuth.substring(7, headerAuth.length()));
        }
        return "Token Doesn't Match Authenfied User";
    }


}
