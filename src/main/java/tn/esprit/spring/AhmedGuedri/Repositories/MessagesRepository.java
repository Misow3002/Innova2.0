package tn.esprit.spring.AhmedGuedri.Repositories;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.AhmedGuedri.entities.Message;

import java.util.List;

@Repository
public interface MessagesRepository extends CrudRepository<Message,Long> {

  //count messages by chatroom
  @Query("SELECT COUNT(m) FROM Message m WHERE m.chatRoom.IdChatRoom = :id and m.Seen = false ")
  public int countMessagesByChatRoom(@Param("id") Long id);


  @Query(value="SELECT text,email FROM user_sent_list,user,message\n" +
          "where user.id=user_sent_list.user_id\n" +
          "and user_sent_list.sent_list_idmsg=message.idmsg ",nativeQuery = true)
    public List<Object[]> ListsentMessagesByChatRoom();

    @Query(value="SELECT text,email FROM user_received_list,user,message\n" +
            "where user.id=user_received_list.user_id\n" +
            "and user_received_list.received_list_idmsg=message.idmsg ",nativeQuery = true)
        public List<Object[]> ListreceivedMessagesByChatRoom();

    //sort messages by date in chatroom
    @Query(value="SELECT sent,text FROM message ,chat_room\n" +
            "where message.chat_room_idchatroom=chat_room.idchatroom\n" +
            "and chat_room.idchatroom=:id\n" +
            "ORDER BY message.sent ASC",nativeQuery = true)
    public List<Object[]> ListMessagesByChatRoom(@Param("id") Long id);

}
