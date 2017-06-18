package kts.project.controller;

import kts.project.controller.dto.RegisterDTO;
import kts.project.controller.dto.RegisterOwnerDTO;
import kts.project.model.Company;
import kts.project.model.Owner;
import kts.project.model.enumerations.Role;
import kts.project.repository.AuthorityRepository;
import kts.project.repository.LocationRepository;
import kts.project.repository.OwnerRepository;
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
@RequestMapping("/api/users/owner")
public class OwnerController {

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;


    //registracija obicnih korisnika!
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveOwner(@RequestBody RegisterOwnerDTO registerOwnerDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Pocinje registracija ownera na backendu!");

        Owner user;

        if (registerOwnerDTO.getRole().equalsIgnoreCase("OWNER")) {

            user = new Owner();
            user.setAuthority(authorityRepository.findByName(("ROLE_OWNER")));

        }
        else {
            System.out.println("ovde je caka");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant create that type of user, ony Customer and Advertiser allowed");
        }

        user.setRole(Role.OWNER);
        user.setName(registerOwnerDTO.getName());
        user.setSurname(registerOwnerDTO.getSurname());
        user.setEmail(registerOwnerDTO.getEmail());
        user.setUsername(registerOwnerDTO.getUsername());
        user.setPassword(encoder.encode(registerOwnerDTO.getPassword()));
        user.setBirthDate(registerOwnerDTO.getBirthDate());
        user.setPhoneNumber(registerOwnerDTO.getPhoneNumber());
        user.setAddress(registerOwnerDTO.getAddress());
        user.setCity(registerOwnerDTO.getCity());
        user.setCountry(registerOwnerDTO.getCountry());
        user.setAccountNumber(registerOwnerDTO.getAccountNumber());
        user.setImageUrl(registerOwnerDTO.getImageUrl());
        //user.setVerifyCode(UUID.randomUUID().toString());

        ownerRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
