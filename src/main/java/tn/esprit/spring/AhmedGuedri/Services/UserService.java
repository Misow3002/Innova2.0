package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
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
    public void updateUser(User u) {
        userRepository.save(u);
        System.out.println("User Modified");
    }

    @Override
    public void deleteUser(User u) {
    userRepository.delete(u);
        System.out.println("User Deleted");
    }
}
