package tn.esprit.spring.AhmedGuedri.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.AhmedGuedri.entities.RolesTypes;
import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByFirstNameOrLastNameContainingIgnoreCase(String firstName, String lastName);

    <Optional>User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.roles = :r")
    public List<User> searchUserRoles(@Param("r") RolesTypes role);

    //sql query count int chatroom of user
    @Query(value = "SELECT COUNT(*) FROM user_chat_rooms r WHERE r.user_id = ?1",
            nativeQuery = true)
    public int countChatRoomByUser(Long userid);




}
