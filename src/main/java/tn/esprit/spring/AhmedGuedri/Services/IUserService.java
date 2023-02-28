package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;

public interface IUserService {

    List<User> retrieveAllUsers();
     void addUser(User u); // Add User
     String updateUser(User u); // Update User
     void deleteUser(String Email); // Delete User


}
