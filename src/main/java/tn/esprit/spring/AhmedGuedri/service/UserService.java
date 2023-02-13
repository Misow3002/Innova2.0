package tn.esprit.spring.AhmedGuedri.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.entities.User;
import tn.esprit.spring.AhmedGuedri.repository.UserRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class UserService implements IUserService{
    UserRepository userRepository;

    @Override
    public List<User> retrieveAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User ajouterUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User e) {
        return userRepository.save(e);
    }

    @Override
    public User retrieveUser(Long idUser) {
        return userRepository.findById(idUser).get();
    }

    @Override
    public User removeUser(Long idUser) {
        userRepository.deleteById(idUser);
        return null;
    }
}
