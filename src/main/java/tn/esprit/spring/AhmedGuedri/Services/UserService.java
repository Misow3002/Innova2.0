package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.ChatRoomRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.MessagesRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.PWDRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;
import tn.esprit.spring.AhmedGuedri.entities.Message;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class UserService implements IUserService{
    UserRepository userRepository;
    PWDRepository pwdRepository;
    MessagesRepository messagesRepository;
    private final ChatRoomRepository chatRoomRepository;

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

    @Override
    public void AffectToChatRoom(String email, ChatRoom r) {
        User u = userRepository.findByEmail(email);
        List<ChatRoom> chatRoomList = new ArrayList<>(u.getChatRooms());
        chatRoomList.add(r);
        u.setChatRooms(chatRoomList);
        userRepository.save(u);
        System.out.println("User Affected to ChatRoom");
    }

    @Override
    public String AddUserToChatRoom(String email, Long r) {

        User u = userRepository.findByEmail(email);
        ChatRoom chatRoom = chatRoomRepository.findById(r).get();
        if (u.getChatRooms().indexOf(chatRoom) != -1) {
            return "User Already in ChatRoom";

        }

        List<ChatRoom> chatRoomList = new ArrayList<>(u.getChatRooms());
        chatRoomList.add(chatRoom);
        u.setChatRooms(chatRoomList);
        userRepository.save(u);
        return "User Added to Existant ChatRoom";
    }

    @Override
    public Boolean VerifyUserInChatRoom(String email, Long r) {
        User u = userRepository.findByEmail(email);
        System.out.println("email : "+u.getEmail());
        ChatRoom chatRoom = chatRoomRepository.findById(r).get();
        System.out.println("chatRoom : "+chatRoom.getNameChat());
        if (u.getChatRooms().indexOf(chatRoom) != -1) {
          //  return "User Can Send Message";
            return true;

        }
        else
            return false;
       // return "ERROR  : User Not in ChatRoom";
    }


    public void SendAndReceive(String Sender,Long IdMsg) {
        User u = userRepository.findByEmail(Sender);
        Message m = messagesRepository.findById(IdMsg).get();
        List<Message> oldSendings = new ArrayList<>(u.getSentList());
        List<Message> oldReceived = new ArrayList<>(u.getReceivedList());
        oldSendings.add(m);
        u.setSentList(oldSendings);
        //---BROADCASTING---//
        List<Long> Receivers = chatRoomRepository.ListUserRelatedtoChatRoom(m.getChatRoom().getIdChatRoom());
        for(Long receiver : Receivers) {
            if (receiver == u.getId()) {
                continue;
            }
            User u2 = userRepository.findById(receiver).get();
            System.out.println("Receiver : "+u2.getEmail());
            List<Message> oldReceived2 = new ArrayList<>(u2.getReceivedList());
            oldReceived2.add(m);
            u2.setReceivedList(oldReceived2);
            userRepository.save(u2);
            System.out.println("Message Sent From : "+u.getEmail()+" To : "+u2.getEmail());
        }


        System.out.println("Message Sent");
   }
    public int countChatRoomByUser(Long userid){
        return userRepository.countChatRoomByUser(userid);
    }
}
