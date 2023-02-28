package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.PWDRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;
@Service
@AllArgsConstructor
public class UserService implements IUserService{

    UserRepository userRepository;

    PWDRepository pwdRepository;
    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User u) {
         userRepository.save(u);
        System.out.println("User Created");
    }

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
        System.out.println("User Deleted");
    }
}
