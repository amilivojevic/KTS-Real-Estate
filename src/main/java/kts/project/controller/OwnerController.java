package kts.project.controller;

import kts.project.controller.dto.RegisterDTO;
import kts.project.controller.dto.RegisterOwnerDTO;
import kts.project.model.*;
import kts.project.model.enumerations.AdvertisementState;
import kts.project.model.enumerations.Role;
import kts.project.repository.*;
import kts.project.service.EmailService;
import kts.project.service.UserService;
import kts.project.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Autowired
    RealEstateRepository realEstateRepository;
    


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
        emailService.sendMail(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAllUsers(@RequestHeader("X-Auth-Token") String token)
    {
        User user = userService.findByToken(token);
        if (user.getRole() == Role.SYS_ADMIN){
            List<Owner> allOwners = new ArrayList<Owner>();

            for (User o : userRepository.findAll()) {
                if (o.getRole() == Role.OWNER){
                    allOwners.add((Owner)o);
                }
            }
            return new ResponseEntity<>(allOwners, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not system administrator!"), HttpStatus.OK);
    }

    @RequestMapping(value = "/erase/{username}", method = RequestMethod.GET)
    public ResponseEntity erase(@PathVariable String username, @RequestHeader("X-Auth-Token") String token)
    {
        User user = userService.findByToken(token);
        if (user.getRole() == Role.SYS_ADMIN){

            User eraseUser = userRepository.findByUsername(username);

            for (Advertisement a : adve)

            for (RealEstate re : realEstateRepository.findAll()) {
                if(re.getOwner().getUsername().equals(username)){
                    realEstateRepository.delete(re.getId());
                }
            }



            ownerRepository.delete((Owner)eraseUser);
            userRepository.delete(eraseUser);
            //treba obrisati i sve one iste!!!!
            return new ResponseEntity<>(eraseUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not system administrator!"), HttpStatus.OK);
    }

}
