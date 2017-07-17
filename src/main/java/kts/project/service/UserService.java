package kts.project.service;

import kts.project.model.PrivateAccountInCompany;
import kts.project.model.User;
import kts.project.repository.UserRepository;
import kts.project.security.TokenUtils;
import kts.project.security.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Korisnik on 6/14/2017.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private TokenUtils tokenUtils;



    @Autowired
    private UserDetailsService userDetailsService;



    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User findByToken(String token){
        String un = tokenUtils.getUsernameFromToken(token);
        UserDetails details = userDetailsService.loadUserByUsername(un);

        System.out.println("username : " + details.getUsername());
        User user = findByUsername(details.getUsername());

        return user;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User u){
        return userRepository.save(u);
    }

    public void delete(User eraseUser){
         userRepository.delete(eraseUser);
    }

}
