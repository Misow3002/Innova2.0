package tn.esprit.spring.AhmedGuedri.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "ChatRoom")
public class ChatRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idchatroom")
    private Long IdChatRoom;
    private String NameChat;
    private boolean visibility;
    private Boolean IsActive;
    //Relation-->Message
    @OneToMany(mappedBy = "chatRoom")
    List<Message> messages;
    //Relation-->User

}

