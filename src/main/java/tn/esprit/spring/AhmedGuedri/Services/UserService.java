package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.AhmedGuedri.Repositories.FeedbacksRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.PWDRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.HashedPWD;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserService implements IUserService{

    UserRepository userRepository;
    FeedbacksRepository feedbacksRepository;
    PWDRepository pwdRepository;
    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void addUser(User u) {

        u.setToken(TokenGenerator(7));
        u.setJoined(new Date());
         userRepository.save(u);
        System.out.println("User Created");
    }
    @Transactional
    @Override
    public String updateUser(User u) {
        System.out.println(u);
        User u2=userRepository.findByEmailEquals(u.getEmail());
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
        userRepository.save(u2);



        return ("User Updated");
    }

    @Override
    public void deleteUser(String Email) {
   User u= userRepository.findByEmailEquals(Email);
   u.setEnabled(true);
    userRepository.save(u);
        System.out.println("User Disabled");
    }

    public String VerifyUserToken(String Email,Long token){
        User u= userRepository.findByEmailEquals(Email);
        if(u.getToken()==token){
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

        return "Token Sent";
    }

    @Override
    public String VerifyForgotPasswordToken(String Email, Long token) {
        User u= userRepository.findByEmailEquals(Email);
        if(u.getToken()==token){
            u.setToken(0L);
            userRepository.save(u);
            //GRANT PERMISSION TO CHANGE PASSWORD --> Redirect to Change Password Page
            return ("User Can Change Password");
        }
        return  "Wrong Token";
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
        return Long.parseLong(token);

    }


}
