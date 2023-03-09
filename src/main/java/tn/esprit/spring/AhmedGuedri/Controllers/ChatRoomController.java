package tn.esprit.spring.AhmedGuedri.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Services.IChatRoomService;
import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;

import tn.esprit.spring.AhmedGuedri.Services.IUserService;
import tn.esprit.spring.AhmedGuedri.Services.UserService;
import tn.esprit.spring.AhmedGuedri.entities.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/chatroom/")
public class ChatRoomController {
    private IChatRoomService iChatRoomService;
    private UserService userService;
    IUserService iUserService;

    @GetMapping("/retrieve-ChatRoom/{ChatRoom-id}")
    public ChatRoom retrieveChatRoom(@PathVariable("ChatRoom-id") String id) {
        return iChatRoomService.retrieveChatRoom(id);
    }


    @PostMapping("addChatroom")

    public ResponseEntity<ChatRoom> AjouterChatrooms(@RequestBody ChatRoom ce,String email) {

        System.out.printf(String.valueOf(ce));

        iChatRoomService.AjouterChatrooms(email,ce);

        return new ResponseEntity<ChatRoom>(HttpStatus.CREATED);

    }


    //AddUserToChatRoom
    @PostMapping("AddUserToChatRoom")
    public ResponseEntity<ChatRoom> AddUserToChatRoom(Long  ce, String email) {

        System.out.printf(String.valueOf(ce));

        iUserService.AddUserToChatRoom(email,ce);

        return new ResponseEntity<ChatRoom>(HttpStatus.CREATED);

    }
    @PutMapping("/updatechatroom")
    public ChatRoom updatechatroom(@RequestBody ChatRoom e, String email) {

        return iChatRoomService.Updatechatrooms(e);

    }


    @PutMapping("/Leavechatroom/{idchatroom}")
    public ChatRoom RemoveChatrooms(@PathVariable("idchatroom") Long idchatroom) {

        return iChatRoomService.RemoveChatrooms(idchatroom);
    }
    /*
    //getUserByName
    @GetMapping("/getUserByName/{name}")
    public User getUserByName(@PathVariable("name") String name) {
        return iChatRoomService.getUserByName(name);
    }*/
//searchChatRoomByName
    /*
    @GetMapping("/searchChatRoomByName/{name}")
    public List<ChatRoom> searchChatRoomByName(@PathVariable("name") String name) {
        return iChatRoomService.searchChatRoomByName(name);
    }*/
    //retrieveAllChatroomsSortedByName
    @GetMapping("/retrieveAllChatroomsSortedByName/{name}")
    public List<ChatRoom> retrieveAllChatroomsSortedByName(@PathVariable("name") String name) {
        return iChatRoomService.retrieveAllChatroomsSortedByName(name);
    }
    //searchUserByFirstLastName
    @GetMapping("/searchUserByFirstLastName/{First}/{Last}")
    public List<User> searchUserByFirstLastName(@PathVariable("First") String First,@PathVariable("Last") String Last) {
        return iChatRoomService.searchUserByFirstLastName(First,Last);
    }
    //searchUserByRole
    @GetMapping("/searchUserByRole/{role}")
    public List<User> searchUserByRoles(@PathVariable("role") RolesTypes role) {
        return iChatRoomService.searchUserRoles(role);
    }
    //getInquiryByCategory
    @GetMapping("/getInquiryByCategory/{productCategory}")
    public List<Inquiry> getInquiryByCategory(@PathVariable("productCategory") ProductCategory productCategory) {
        return iChatRoomService.getInquiryByCategory(productCategory);
    }
    //public int countChatRoomByUser(Long userid);
    @GetMapping("/countChatRoomByUser/{userid}")
    public int countChatRoomByUser(@PathVariable("userid") Long userid) {
        return iChatRoomService.countChatRoomByUser(userid);
    }
    //listUsersByChatRoom
    @GetMapping("/listUsersByChatRoom/{chatroomid}")
    public List<Object[]> listUsersByChatRoom(@PathVariable("chatroomid") Long chatroomid) {
        return iChatRoomService.ListUsersByChatRoom(chatroomid);
    }
}
