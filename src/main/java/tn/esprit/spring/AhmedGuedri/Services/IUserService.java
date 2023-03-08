package tn.esprit.spring.AhmedGuedri.Services;

import com.sun.org.apache.xpath.internal.operations.Bool;
import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;

public interface IUserService {

    List<User> retrieveAllUsers();
     void addUser(User u); // Add User
     void updateUser(User u); // Update User
     void deleteUser(User u); // Delete User

    void AffectToChatRoom(String email, ChatRoom r);
    String AddUserToChatRoom(String email, Long r);
    Boolean VerifyUserInChatRoom(String email, Long r);
    public void SendAndReceive(String Sender,Long IdMsg);



}
