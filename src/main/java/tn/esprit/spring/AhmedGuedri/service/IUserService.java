package tn.esprit.spring.AhmedGuedri.service;

import tn.esprit.spring.AhmedGuedri.entities.User;

import java.util.List;

public interface IUserService {
    List<User> retrieveAllUsers();

    User ajouterUser (User user);

    User updateUser (User e);


    User retrieveUser(Long idUser);


    User removeUser(Long idUser);
}
