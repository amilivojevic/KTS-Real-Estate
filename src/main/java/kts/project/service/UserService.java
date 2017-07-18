package kts.project.service;

import kts.project.controller.dto.RegisterPrivateAccDTO;
import kts.project.model.PrivateAccountInCompany;
import kts.project.model.User;
import kts.project.repository.UserRepository;
import kts.project.security.TokenUtils;
import kts.project.security.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * This class represents User Service
 *
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private TokenUtils tokenUtils;



    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    PrivateAccountInCompanyService privateAccountInCompanyService;



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

    /**
     * This method is checking if Owner is private Owner inside Company
     * @param ownerId
     * @return id of Owner inside company if found, -1 if not
     */
    public long checkIfOwnerIsPrivate(long ownerId){
        for(PrivateAccountInCompany p : privateAccountInCompanyService.findAll()){
            if(p.getOwner().getId() == ownerId){
                return p.getId();
            }
        }
        return -1;
    }

    /**
     * This method is checking if all required inputs for RealEstateDTO are entered
     * @param registerPrivateAccDTO
     * @return true or false
     */
    public boolean checkPrivateAccDTOInput(RegisterPrivateAccDTO registerPrivateAccDTO) {

        if (registerPrivateAccDTO == null) {
            return false;
        }
        if (registerPrivateAccDTO.getType().equals("") ||
                registerPrivateAccDTO.getRole().equals("") ||
                registerPrivateAccDTO.getUsername().equals("") ||
                registerPrivateAccDTO.getPassword().equals("") ||
                registerPrivateAccDTO.getEmail().equals("") ||
                registerPrivateAccDTO.getName().equals("") ||
                registerPrivateAccDTO.getSurname().equals("") ||
                registerPrivateAccDTO.getBirthDate().equals("") ||
                registerPrivateAccDTO.getPhoneNumber().equals("") ||
                registerPrivateAccDTO.getAddress().equals("") ||
                registerPrivateAccDTO.getCity().equals("") ||
                registerPrivateAccDTO.getCountry().equals("") ||
                registerPrivateAccDTO.getAccountNumber().equals("") ||
                registerPrivateAccDTO.getImageUrl().equals("") ||
                registerPrivateAccDTO.getCompanyId() < 0) {
            return false;
        } else {
            return true;
        }
    }

}
