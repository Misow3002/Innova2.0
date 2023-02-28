package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.PWDRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.HashedPWD;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PWDService implements IPWDService {
    UserRepository userRepository;
    PWDRepository pwdRepository;


    @Override
    public void AssignPasswordToUser(User u, String password) {
        HashedPWD newUserPass = new HashedPWD();
        Date date = new Date();
        newUserPass.setPassword(password);
        newUserPass.setChangeDate(date);
        newUserPass.setUser(u);
        pwdRepository.save(newUserPass);
        u.setHashedPWD(pwdRepository.findByUser(u));
        userRepository.save(u);
    }

    @Override
    public String EditPassword (String Email,String PrevPassword,String NewPassword){
        User user = userRepository.findByEmailEquals(Email);
        System.out.println(user);
        Date datenow = new Date();
        if (user.isEnabled()==false) {
            HashedPWD pass = pwdRepository.findById(user.getHashedPWD().getPassId()).get();
            System.out.println(pass);
            long diff = datenow.getTime() - pass.getChangeDate().getTime();
            if( ((diff / (24 * 60 * 60 * 1000) <= 14))){
                if (PrevPassword.equals(pass.getPassword())) {
                    System.out.println(PrevPassword);
                    System.out.println(NewPassword);
                    System.out.printf("Password Changed");
                    pass.setPassword(NewPassword);
                    pass.setChangeDate(datenow);
                    pwdRepository.save(pass);
                } else
                    System.out.println("Wrong Password");
            } else
                System.out.println("Password Date Not yet Expired");
        }
        return "Password Changed";
    }
//            HashedPWD newUserPass = new HashedPWD();
//            Date date = new Date();
//            newUserPass.setPassword(password);
//            newUserPass.setChangeDate(date);
//            newUserPass.setUser(u);
//            pwdRepository.save(newUserPass);


    @Override
    public String RetievePasswordInfo(String email) {
        User u=userRepository.findByEmailEquals(email);
        System.out.println("el user :"+u);
        return pwdRepository.findByUser(u).getChangeDate().toString();
    }
}
