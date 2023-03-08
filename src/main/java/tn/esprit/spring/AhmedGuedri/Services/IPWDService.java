package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;

public interface IPWDService {
     void AssignPasswordToUser(User u,String password); // Assign Password To User
     void EditPassword(User u,String password); // Update Password
     void RetievePasswordInfo(User u); // Retrieve User


}
