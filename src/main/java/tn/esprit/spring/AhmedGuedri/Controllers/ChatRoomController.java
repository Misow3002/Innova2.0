package tn.esprit.spring.AhmedGuedri.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Services.IChatRoomService;
import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;

@AllArgsConstructor
@RestController
@RequestMapping("/chatroom/")
public class ChatRoomController {


    private IChatRoomService iChatRoomService;

    @GetMapping("/retrieve-ChatRoom/{ChatRoom-id}")
    public ChatRoom retrieveChatRoom(@PathVariable("ChatRoom-id") String id) {
        return iChatRoomService.retrieveChatRoom(id);
    }


    @PostMapping("addChatroom")
    public ResponseEntity<ChatRoom> AjouterChatrooms(@RequestBody ChatRoom ce) {

        System.out.printf(String.valueOf(ce));

        iChatRoomService.AjouterChatrooms(ce);

        return new ResponseEntity<ChatRoom>(HttpStatus.CREATED);

    }


    @PutMapping("/updatechatroom")
    public ChatRoom updatechatroom(@RequestBody ChatRoom e) {

        return iChatRoomService.Updatechatrooms(e);

    }

    @PutMapping("/removechatroom/{idchatroom}")
    public ChatRoom RemoveChatrooms(@PathVariable("idchatroom") Long idchatroom) {

        return iChatRoomService.RemoveChatrooms(idchatroom);

    }
}
