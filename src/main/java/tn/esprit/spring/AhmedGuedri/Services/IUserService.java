package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;

public interface IUserService {

    List<User> retrieveAllUsers();
     void addUser(User u); // Add User
     void updateUser(User u); // Update User
     void deleteUser(User u); // Delete User


}
