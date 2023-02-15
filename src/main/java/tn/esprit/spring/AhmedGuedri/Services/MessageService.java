package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.ChatRoomRepository;
import tn.esprit.spring.AhmedGuedri.Repositories.MessagesRepository;
import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;
import tn.esprit.spring.AhmedGuedri.entities.Message;

import java.util.List;



@Service
@AllArgsConstructor
public class MessageService implements IMessageService{

    MessagesRepository messagesRepository;
    ChatRoomRepository chatRoomRepository;
    @Override
    public List<Message> retrieveAllMessages() {
        return (List<Message>) messagesRepository.findAll();
    }

    @Override
    public Message AjouterMessages(Message Message,Long ChatRoom) {
        ChatRoom cr = chatRoomRepository.findById(ChatRoom).get();
        Message.setChatRoom(cr);
        return messagesRepository.save(Message);
    }

    @Override
    public Message UpdateMessages(Message e,Long ChatRoom) {
        ChatRoom cr = chatRoomRepository.findById(ChatRoom).get();
        e.setChatRoom(cr);
        return messagesRepository.save(e);
    }

    @Override
    public Message RetrieveMessages(Long idMessage) {
        return messagesRepository.findById(idMessage).get();
    }

    @Override
    public Message RemoveMessages(Long idMessage) {
        messagesRepository.deleteById(idMessage);
        return null;
    }

}
