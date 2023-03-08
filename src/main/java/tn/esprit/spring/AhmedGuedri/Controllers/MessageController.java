package tn.esprit.spring.AhmedGuedri.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.AhmedGuedri.Services.IMessageService;
import tn.esprit.spring.AhmedGuedri.entities.Message;

@AllArgsConstructor
@RestController
@RequestMapping("/message/")
public class MessageController {
    private IMessageService iMessageService;
    @GetMapping("/Retrieve-Messages/{id}")
    public Message RetrieveMessages(@PathVariable("id") Long Message) {
        return iMessageService.RetrieveMessages(Message);
    }


    @PostMapping("addMessage")
    public ResponseEntity<Message> AjouterMessages(@RequestBody Message Message, Long IdChatRoom) {

        System.out.printf(String.valueOf(Message));

        iMessageService.AjouterMessages(Message,IdChatRoom);

        return new ResponseEntity<Message>(HttpStatus.CREATED);

    }


    @PutMapping("/updateidMessage")
    public Message updateMessage(@RequestBody Message e, Long IdChatRoom) {

        return iMessageService.UpdateMessages(e,IdChatRoom);

    }

    @PutMapping("/removeMessage/{idMessage}")
    public Message RemoveMessages(@PathVariable("idMessage") Long idMessage) {

        return iMessageService.RemoveMessages(idMessage);

    }
}
