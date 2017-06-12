package kts.project.controller;

import kts.project.controller.dto.RegisterDTO;
import kts.project.model.Owner;
import kts.project.model.User;
import kts.project.model.enumerations.Role;
import kts.project.repository.AuthorityRepository;
import kts.project.repository.LocationRepository;
import kts.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by USER on 6/11/2017.
 */


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "/owner/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveCustomer(@RequestBody RegisterDTO registerDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user;

        if (registerDTO.getRole().equalsIgnoreCase("OWNER")) {
            user = new Owner();
            user.setRole(Role.OWNER);
            user.setAuthority(authorityRepository.findByName(("ROLE_OWNER")));
        }

        else if (registerDTO.getRole().equalsIgnoreCase("VERIFYER")) {
            user = new User();
            user.setRole(Role.VERIFYER);
            user.setAuthority(authorityRepository.findByName(("ROLE_VERIFYER")));

        }

        else if (registerDTO.getRole().equalsIgnoreCase("ADMIN")) {
            user = new User();
            user.setRole(Role.SYS_ADMIN);
            user.setAuthority(authorityRepository.findByName(("ROLE_ADMIN")));

        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant create that type of user, ony Customer and Advertiser allowed");
        }

        user.setName(registerDTO.getName());
        user.setSurname(registerDTO.getSurname());
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        user.setBirthDate(registerDTO.getBirthDate());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setAddress(locationRepository.findOne(Long.valueOf(registerDTO.getAddressId())));
        user.setAccountNumber(registerDTO.getAccountNumber());
        user.setImageUrl(registerDTO.getImageUrl());
        //user.setVerifyCode(UUID.randomUUID().toString());

        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
