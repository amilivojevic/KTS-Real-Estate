package kts.project.service;

import kts.project.model.User;
import kts.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Korisnik on 6/14/2017.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
