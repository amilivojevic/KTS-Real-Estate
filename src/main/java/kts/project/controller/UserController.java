package kts.project.controller;

import kts.project.controller.dto.*;
import kts.project.model.Company;
import kts.project.model.Owner;
import kts.project.model.PrivateAccountInCompany;
import kts.project.model.User;
import kts.project.model.enumerations.Role;
import kts.project.repository.*;
import kts.project.security.TokenUtils;
import kts.project.security.UserUtils;
import kts.project.service.EmailService;
import kts.project.service.UserService;
import kts.project.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 6/11/2017.
 */


@RestController
    @RequestMapping("/api/users")
    public class UserController {

        @Autowired
        private AuthorityRepository authorityRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        private TokenUtils tokenUtils;

        @Autowired
        private EmailService emailService;

        @Autowired
        private UserService userService;

        @Autowired
        private PrivateAccountInCompanyRepository privateAccountInCompanyRepository;

        @Autowired
        private OwnerRepository ownerRepository;

        @Autowired
        private CompanyRepository companyRepository;




    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        try {
            System.out.println("*** Pocinje login na backendu");
            // Perform the authentication
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                    loginDTO.getPassword());
            System.out.println("token : " + token);
            Authentication authentication = authenticationManager.authenticate(token);
            System.out.println("posle authentication");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("linija PRE loadUserByUsername");
            // Reload user details so we can generate token
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            System.out.println("linija POSLE loadUserByUsername");
            return new ResponseEntity<>(new ResponseMessage(tokenUtils.generateToken(details)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseMessage("Invalid login"),
                    HttpStatus.NOT_FOUND);
        }
    }

    //registracija administratora i verifikatora!
    @RequestMapping(value = "/{role}/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveUser(@PathVariable String role,@RequestBody RegisterDTO registerDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Pocinje registracija na backendu!");
        User user;

        if (registerDTO.getRole().equalsIgnoreCase("VERIFYER")) {
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
            System.out.println("ovde je caka");
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
        user.setAddress(registerDTO.getAddress());
        user.setCity(registerDTO.getCity());
        user.setCountry(registerDTO.getCountry());
        user.setAccountNumber(registerDTO.getAccountNumber());
        user.setImageUrl(registerDTO.getImageUrl());
        //user.setVerifyCode(UUID.randomUUID().toString());
        userRepository.save(user);

        emailService.sendMail(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //registracija obicnih korisnika unutar firme
    @RequestMapping(value = "privateAcc/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveOwner(@RequestBody RegisterPrivateAccDTO registerPrivateAccDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Pocinje registracija ownera unutar firme na backendu!");

        Owner user;

        if (registerPrivateAccDTO.getRole().equalsIgnoreCase("OWNER")) {

            user = new Owner();
            user.setAuthority(authorityRepository.findByName(("ROLE_OWNER")));

        }
        else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant create that type of user, ony Customer and Advertiser allowed");
        }

        user.setRole(Role.OWNER);
        user.setName(registerPrivateAccDTO.getName());
        user.setSurname(registerPrivateAccDTO.getSurname());
        user.setEmail(registerPrivateAccDTO.getEmail());
        user.setUsername(registerPrivateAccDTO.getUsername());
        user.setPassword(encoder.encode(registerPrivateAccDTO.getPassword()));
        user.setBirthDate(registerPrivateAccDTO.getBirthDate());
        user.setPhoneNumber(registerPrivateAccDTO.getPhoneNumber());
        user.setAddress(registerPrivateAccDTO.getAddress());
        user.setCity(registerPrivateAccDTO.getCity());
        user.setCountry(registerPrivateAccDTO.getCountry());
        user.setAccountNumber(registerPrivateAccDTO.getAccountNumber());
        user.setImageUrl(registerPrivateAccDTO.getImageUrl());

        ownerRepository.save(user);

        PrivateAccountInCompany privateAcc = new PrivateAccountInCompany();
        privateAcc.setApproved(false);
        privateAcc.setCompany(companyRepository.findOne(registerPrivateAccDTO.getCompanyId()));
        privateAcc.setOwner(ownerRepository.findByUsername(registerPrivateAccDTO.getUsername()));

        privateAccountInCompanyRepository.save(privateAcc);

        emailService.sendMail(user);

        return new ResponseEntity<>(privateAcc, HttpStatus.OK);
    }

    //registracija administratora i verifikatora!
    @RequestMapping(value = "/{role}/modify", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity modifyUser(@PathVariable String role,@RequestBody RegisterDTO changedAdminDTO,@RequestHeader("X-Auth-Token") String token) {
        User user = userService.findByToken(token);
        user.setName(changedAdminDTO.getName());
        user.setSurname(changedAdminDTO.getSurname());
        user.setEmail(changedAdminDTO.getEmail());
        user.setBirthDate(changedAdminDTO.getBirthDate());
        user.setPhoneNumber(changedAdminDTO.getPhoneNumber());
        user.setAddress(changedAdminDTO.getAddress());
        user.setCity(changedAdminDTO.getCity());
        user.setCountry(changedAdminDTO.getCountry());
        user.setAccountNumber(changedAdminDTO.getAccountNumber());
        user.setImageUrl(changedAdminDTO.getImageUrl());
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }




    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getData(@RequestHeader("X-Auth-Token") String token)
    {
        User user = userService.findByToken(token);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAllUsers(@RequestHeader("X-Auth-Token") String token)
    {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "verifier/getAll", method = RequestMethod.GET)
    public ResponseEntity getAllVerifiers(@RequestHeader("X-Auth-Token") String token)
    {
        User user = userService.findByToken(token);
        if (user.getRole() == Role.SYS_ADMIN){
            List<User> allVerifiers = new ArrayList<User>();

            for (User v : userRepository.findAll()) {
                if (v.getRole() == Role.VERIFYER){
                    allVerifiers.add((User) v);
                }
            }
            return new ResponseEntity<>(allVerifiers, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not system administrator!"), HttpStatus.OK);
    }

    @RequestMapping(value = "/verifier/erase/{username}", method = RequestMethod.GET)
    public ResponseEntity erase(@PathVariable String username, @RequestHeader("X-Auth-Token") String token)
    {
        User user = userService.findByToken(token);
        if (user.getRole() == Role.SYS_ADMIN){

            User eraseUser = userRepository.findByUsername(username);

            //obrisati i sve one stvari verifikatora!!!
            userRepository.delete(eraseUser);

            return new ResponseEntity<>(eraseUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not system administrator!"), HttpStatus.OK);
    }

}
