package kts.project.controller;

import kts.project.controller.dto.LoginDTO;
import kts.project.controller.dto.RegisterDTO;
import kts.project.controller.dto.RegisterOwnerDTO;
import kts.project.controller.dto.UserDTO;
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

/**
 * Created by USER on 6/11/2017.
 */


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private EmailService emailService;

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


    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getData(@RequestHeader("X-Auth-Token") String token)
    {
        String un = tokenUtils.getUsernameFromToken(token);
        System.out.println("username = " + un);
        UserDetails details = userDetailsService.loadUserByUsername(un);
        if(details == null){
            System.out.println("Details = null");
        }
        else{
            System.out.println("detail nije null" + details.toString());
        }
        System.out.println("username : " + details.getUsername());
        User user = userService.findByUsername(details.getUsername());



        /*if (request == null){
            System.out.println("request = null");
        }
        else{
            System.out.println("request nijr null");
        }
        //System.out.println("REQUEST = " + request.getContentType());
        User user = (User)userUtils.getLoggedUser(request);
        if (user != null){
            System.out.println("USER: "+user.toString());
        }
        else{
            System.out.println("mrs ");
        }*/
        return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
    }

}
