package tn.esprit.spring.AhmedGuedri.Services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.ChatRoomRepository;
import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;

@Service

@AllArgsConstructor
public class ChatRoomService implements IChatRoomService{

    ChatRoomRepository chatRoomRepository;

    @Override
    public List<ChatRoom> retrieveAllChatrooms() {
        return (List<ChatRoom>) chatRoomRepository.findAll();
    }

    @Override
    public ChatRoom AjouterChatrooms(ChatRoom chatroom) {
        return chatRoomRepository.save(chatroom);
    }

    @Override
    public ChatRoom Updatechatrooms(ChatRoom e) {
        return chatRoomRepository.save(e);
    }


    @Override
    public ChatRoom RemoveChatrooms(Long idchatroom) {
        chatRoomRepository.deleteById(idchatroom);
        return null;
    }

    @Override
    public ChatRoom retrieveChatRoom(String id) {
        return chatRoomRepository.findById(Long.parseLong(id)).get();
    }
}
