package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {

    List<User> retrieveAllUsers();
     void addUser(User u); // Add User
     String updateUser(User u); // Update User
     String deleteUser(String Email); // Delete User

    String ActivateUser(String Email); // Activate User

    String VerifyUserToken(String Email,Long token); // Verify User Token
    //USER forgot password
    String ForgotPassword(String Email,Boolean EmailorPhone,String phonenumber); // Forgot Password
    String VerifyForgotPasswordToken(String Email,String PrevPass,String NewPass,Long token); // Verify Forgot Password Token

    List<String> TopTierSellers();
    void AntiBot();
    int NumberOfSubs();

    void Authenticate(String Email);

    Long TokenGenerator(int ends);

    String UserVerificationReturnEmail(HttpServletRequest request);

    String SendSMS(String to, String body);


}
