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
        if (user.isDisabled()) {
            HashedPWD pass = pwdRepository.findById(u.getId()).get();
            HashedPWD newUserPass = new HashedPWD();
            Date date = new Date();
            newUserPass.setPassword(password);
            newUserPass.setChangeDate(date);
            newUserPass.setUser(u);
            pwdRepository.save(newUserPass);
        }
    }

    @Override
    public void RetievePasswordInfo(User u) {

    }
}
