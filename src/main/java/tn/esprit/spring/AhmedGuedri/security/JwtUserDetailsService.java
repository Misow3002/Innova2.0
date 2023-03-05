package tn.esprit.spring.AhmedGuedri.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.AhmedGuedri.Repositories.UserRepository;
import tn.esprit.spring.AhmedGuedri.Services.UserService;
import tn.esprit.spring.AhmedGuedri.entities.HashedPWD;

@Service
public class JwtUserDetailsService implements UserDetailsService {

UserService userService;
@Autowired
UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // userService.Authenticate("Mahdi.gherir@esprit.tn");
        // FETCHING

       // System.out.println("FETCHING USER");
        List<Object[]> user = userRepository.Authentification(username);
        if (user.size() != 0) {
            tn.esprit.spring.AhmedGuedri.entities.User u = (tn.esprit.spring.AhmedGuedri.entities.User) user.get(0)[0];
            tn.esprit.spring.AhmedGuedri.entities.HashedPWD h = (tn.esprit.spring.AhmedGuedri.entities.HashedPWD) user.get(0)[1];
          //  System.out.println(u.getEmail() + " | " + h.getPassword());
            if (u.isEnabled() == true) {
                return new User(u.getEmail(), h.getPassword(), new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("User Account is Disabled ");
            }
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
                //END FETCHING

//        if ("javainuse".equals(username)) {
//            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }

}