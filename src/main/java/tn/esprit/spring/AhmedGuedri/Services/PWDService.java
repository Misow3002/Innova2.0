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
public class PWDService implements IPWDService{
    UserRepository userRepository;
    PWDRepository pwdRepository;


    @Override
    public void AssignPasswordToUser(User u,String password) {
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
    public void EditPassword(User u,String password) {
        User user = userRepository.findById(u.getId()).get();
        Date datenow = new Date();
        if (user.isDisabled()) {
            HashedPWD pass = pwdRepository.findById(u.getId()).get();
            long diff = datenow.getTime() - pass.getChangeDate().getTime();
            if ((diff / (24 * 60 * 60 * 1000) <= 14)) {
                System.out.printf("Password Changed");
                pass.setPassword(password);
                pass.setChangeDate(datenow);
                pwdRepository.save(pass);
            }
            else
                System.out.println("Password Date Not yet Expired");
        }
    }
//            HashedPWD newUserPass = new HashedPWD();
//            Date date = new Date();
//            newUserPass.setPassword(password);
//            newUserPass.setChangeDate(date);
//            newUserPass.setUser(u);
//            pwdRepository.save(newUserPass);


    @Override
    public Date RetievePasswordInfo(User u) {
        return pwdRepository.findByUser(u).getChangeDate();
    }
}
