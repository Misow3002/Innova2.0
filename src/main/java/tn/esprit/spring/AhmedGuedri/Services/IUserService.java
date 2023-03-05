package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;

public interface IUserService {

    List<User> retrieveAllUsers();
     void addUser(User u); // Add User
     String updateUser(User u); // Update User
     void deleteUser(String Email); // Delete User

    String VerifyUserToken(String Email,Long token); // Verify User Token
    //USER forgot password
    String ForgotPassword(String Email); // Forgot Password
    String VerifyForgotPasswordToken(String Email,Long token); // Verify Forgot Password Token

    List<String> TopTierSellers();
    void AntiBot();
    int NumberOfSubs();

    void Authenticate(String Email);

    Long TokenGenerator(int ends);


}
