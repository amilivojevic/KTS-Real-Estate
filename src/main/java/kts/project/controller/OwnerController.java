package kts.project.controller;

import kts.project.controller.dto.RegisterDTO;
import kts.project.controller.dto.RegisterOwnerDTO;
import kts.project.model.Advertisement;
import kts.project.model.Company;
import kts.project.model.Owner;
import kts.project.model.User;
import kts.project.model.*;
import kts.project.model.enumerations.AdvertisementState;
import kts.project.model.enumerations.Role;
import kts.project.repository.*;
import kts.project.service.*;
import kts.project.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *This class represents controller for Owner and manages with all Real Estate
 * functionalities.
 */
@RestController
@RequestMapping("/api/users/owner")
public class OwnerController {

    @Autowired
    OwnerService ownerService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    RealEstateService realEstateService;

    /**
     * This method represents registration of Owner
     * @param registerOwnerDTO
     * @return ResponseEntity with HttpStatus OK if everything is OK or BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveOwner(@RequestBody RegisterOwnerDTO registerOwnerDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Pocinje registracija ownera na backendu!");

        if (!ownerService.checkPrivateAccountInCompanyDTOInput(registerOwnerDTO)){
            return new ResponseEntity<>(new ResponseMessage("New Owner input is not valid (some fields are null)"), HttpStatus.BAD_REQUEST);
        }

        Owner user;

        if (registerOwnerDTO.getRole().equalsIgnoreCase("OWNER")) {

            user = new Owner();
            user.setAuthority(authorityService.findByName(("ROLE_OWNER")));

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

        ownerService.save(user);
        emailService.sendMail(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     *  This method gets all Owners
     * @param token
     * @return ResponseEntity with HttpStatus OK if everything is OK or BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAllUsers(@RequestHeader("X-Auth-Token") String token)
    {
        User user = userService.findByToken(token);
        if (user.getRole() == Role.SYS_ADMIN){
            List<Owner> allOwners = new ArrayList<Owner>();

            for (User o : userService.findAll()) {
                if (o.getRole() == Role.OWNER){
                    allOwners.add((Owner)o);
                }
            }
            return new ResponseEntity<>(allOwners, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not system administrator!"), HttpStatus.BAD_REQUEST);
    }

    /**
     * This method tests getting all Advertisements of a current User
     * @param token
     * @return ResponseEntity with HttpStatus OK if everything is OK, BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/getAllMyAdvertisements", method = RequestMethod.GET)
    public ResponseEntity getAllMyAdvertisements(@RequestHeader("X-Auth-Token") String token) {

        User user = userService.findByToken(token);
        if (user.getRole() == Role.OWNER){

            List<Advertisement> myAdvertisements = new ArrayList<>();

            for (Advertisement o : advertisementService.findAll()) {
                if (o.getOwner().getId() == user.getId()){
                    myAdvertisements.add(o);
                }
            }
            return new ResponseEntity<>(myAdvertisements, HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseMessage("Logged User is not an owner!"), HttpStatus.BAD_REQUEST);
    }

    /**
     * This method is deleting one Owner together with his Advertisements and RealEstates
     * @param username
     * @param token
     * @return ResponseEntity with HttpStatus OK if everything is OK, BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/erase/{username}", method = RequestMethod.GET)
    public ResponseEntity erase(@PathVariable String username, @RequestHeader("X-Auth-Token") String token)
    {
        User user = userService.findByToken(token);
        if (user.getRole() == Role.SYS_ADMIN){

            User eraseUser = userService.findByUsername(username);

            for (Advertisement a : advertisementService.findAll()) {
                if(a.getOwner().getUsername().equals(username)){
                    advertisementService.delete(a.getId());
                }
            }

            for (RealEstate re : realEstateService.findAll()) {
                if(re.getOwner().getUsername().equals(username)){
                    realEstateService.delete(re.getId());
                }
            }
            System.out.println("Erase user" + eraseUser.getUsername());
            ownerService.delete((Owner)eraseUser);
            System.out.println("Erase user" + eraseUser.getUsername());
            userService.delete(eraseUser);

            //treba obrisati i sve one iste!!!!
            return new ResponseEntity<>(eraseUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not system administrator!"), HttpStatus.BAD_REQUEST);

    }

}
