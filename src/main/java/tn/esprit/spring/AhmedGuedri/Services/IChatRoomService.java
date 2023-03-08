package tn.esprit.spring.AhmedGuedri.Services;

import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;

import java.util.List;

public interface IChatRoomService {
    List<ChatRoom> retrieveAllChatrooms();

    ChatRoom AjouterChatrooms (ChatRoom chatroom);

    ChatRoom Updatechatrooms (ChatRoom e);





    ChatRoom RemoveChatrooms(Long idchatroom);

    public ChatRoom retrieveChatRoom(String id);

}
