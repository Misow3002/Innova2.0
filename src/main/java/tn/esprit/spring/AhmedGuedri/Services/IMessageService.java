package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;
import tn.esprit.spring.AhmedGuedri.entities.Message;

import java.util.List;

public interface IMessageService {

    List<Message> retrieveAllMessages();

    Message AjouterMessages (Message Message, Long IdChatRoom);

    Message UpdateMessages (Message e,Long ChatRoom);


    Message RetrieveMessages(Long idMessage);


    Message RemoveMessages(Long idMessage);

}
