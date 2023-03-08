package tn.esprit.spring.AhmedGuedri.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.ChatRoom;

@Repository
public interface ChatRoomRepository extends CrudRepository <ChatRoom,Long> {
}