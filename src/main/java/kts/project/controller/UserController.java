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
import kts.project.service.*;
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
 *This class represents controller for User and manages with all User
 * functionalities.
 */
@RestController
    @RequestMapping("/api/users")
    public class UserController {

        @Autowired
        private AuthorityService authorityService;

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
        private PrivateAccountInCompanyService privateAccountInCompanyService;

        @Autowired
        private OwnerService ownerService;

        @Autowired
        private CompanyService companyService;

        @Autowired
        private UserUtils userUtils;


    // TESTING COMPLETED
    /**
     *This method represents log in for all types of users.
     *
     * @param loginDTO
     * @return ResponseEntity with HttpStatus OK if everything is OK, BAD_REQUEST if not OK, else NOT_FOUND
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        try {
         /*   System.out.println("*** Pocinje login na backendu"); */

            if(loginDTO.getUsername() == null || loginDTO.getPassword() == null){
                return new ResponseEntity<>(new ResponseMessage("Username or password must be inserted!"), HttpStatus.BAD_REQUEST);
            }
            // Perform the authentication
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                    loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Reload user details so we can generate token
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());

            return new ResponseEntity<>(new ResponseMessage(tokenUtils.generateToken(details)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseMessage("Invalid login"),
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is checking if Company or private Owner in Company are approved
     * @param token
     * @param loginDTO
     * @return ResponseEntity with HttpStatus OK if everything is OK or BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/checkApproved", method = RequestMethod.POST, consumes = "application/json;charset=utf-8")
    public ResponseEntity checkApproved(@RequestHeader("X-Auth-Token") String token,@RequestBody EmptyDTO loginDTO) {
        boolean approved = true;
        User user = userService.findByToken(token);

        if(user.getRole() == Role.OWNER){
            //company
            if(user instanceof Company){
                approved = ((Company) user).isApproved();
            }
            //private account in company
            else if(userService.checkIfOwnerIsPrivate(user.getId()) != -1){
                PrivateAccountInCompany p = privateAccountInCompanyService.findById(userService.checkIfOwnerIsPrivate(user.getId()));
                approved = p.isApproved();
            }
        }
        else{
            return new ResponseEntity<>(approved,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(approved,HttpStatus.OK);

    }

    //TESTED FULLY
    /**
     * This method represents register for Admin and Verifier types of Users, sets authority and saves
     * it to the database.
     * @param role
     * @param registerDTO
     * @return ResponseEntity with HttpStatus CREATED if everything is OK or BAD_REQUEST if not OK
     */
        //registracija administratora i verifikatora!
    @RequestMapping(value = "/{role}/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveUser(@PathVariable String role,@RequestBody RegisterDTO registerDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user;

        if(!userUtils.checkUniqueUsername(registerDTO.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username is not unique!");
        }
        if(registerDTO.getUsername() == null || registerDTO.getEmail() == null || registerDTO.getPassword() == null){
            return new ResponseEntity<>(new ResponseMessage("Username should not be null!"), HttpStatus.BAD_REQUEST);
        }


        if (registerDTO.getRole().equalsIgnoreCase("VERIFYER")) {
            user = new User();
            user.setRole(Role.VERIFYER);
            user.setAuthority(authorityService.findByName(("ROLE_VERIFYER")));

        }

        else if (registerDTO.getRole().equalsIgnoreCase("ADMIN")) {
            user = new User();
            user.setRole(Role.SYS_ADMIN);
            user.setAuthority(authorityService.findByName(("ROLE_ADMIN")));

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

        userService.save(user);

        emailService.sendMail(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // Testing in progress
    /**
     * This method represents registration of private Owners inside Company
     * @param registerPrivateAccDTO
     * @return ResponseEntity with HttpStatus CREATED if everything is OK or BAD_REQUEST if not OK
     */
    //registracija obicnih korisnika unutar firme


    // OVDE SAM DODALA / NA POCETAK LINKA
    // WTF S OVIMEEEE
    @RequestMapping(value = "/privateAcc/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveOwner(@RequestBody RegisterPrivateAccDTO registerPrivateAccDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Pocinje registracija ownera unutar firme na backendu!");


        if (!userService.checkPrivateAccDTOInput(registerPrivateAccDTO)){
            return new ResponseEntity<>(new ResponseMessage("New Real Estate input is not valid (some fields are null)"), HttpStatus.BAD_REQUEST);
        }
        Owner user;

        if (registerPrivateAccDTO.getRole().equalsIgnoreCase("OWNER")) {

            user = new Owner();
            user.setAuthority(authorityService.findByName(("ROLE_OWNER")));

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

        ownerService.save(user);

        PrivateAccountInCompany privateAcc = new PrivateAccountInCompany();
        privateAcc.setApproved(false);
        privateAcc.setCompany(companyService.findById(registerPrivateAccDTO.getCompanyId()));
        privateAcc.setOwner(ownerService.findByUsername(registerPrivateAccDTO.getUsername()));

        privateAccountInCompanyService.save(privateAcc);

        emailService.sendMail(user);

        return new ResponseEntity<>(privateAcc, HttpStatus.CREATED);
    }

    /**
     * This method represents modifying for Admin and Verifier types of users
     * @param role
     * @param changedAdminDTO
     * @param token
     * @return ResponseEntity with HttpStatus OK if everything is OK
     */
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
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Testing COMPLETED
    /**
     * This method is getting all data of an user
     * @param token
     * @return ResponseEntity with HttpStatus Ok if everything is OK
     */
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public ResponseEntity<UserDTO2> getData(@RequestHeader("X-Auth-Token") String token)
    {
        User user = userService.findByToken(token);
        return new ResponseEntity<>(new UserDTO2(user), HttpStatus.OK);
    }

    // Testing COMPLETED
    /**
     * This method returns list of all the users
     * @param token
     * @return ResponseEntity with HttpStatus Ok if everything is OK
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAllUsers(@RequestHeader("X-Auth-Token") String token)
    {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    // Testing COMPLETED WTF SA LINKOM ?????????????????
    /**
     * This method gets all users of type Verifier
     * @param token
     * @return ResponseEntity with HttpStatus Ok if everything is OK or BAD_REQUEST if not OK
     */
    @RequestMapping(value = "verifier/getAll", method = RequestMethod.GET)
    public ResponseEntity getAllVerifiers(@RequestHeader("X-Auth-Token") String token)
    {
        User user = userService.findByToken(token);
        if (user.getRole() == Role.SYS_ADMIN){
            List<User> allVerifiers = new ArrayList<User>();

            for (User v : userService.findAll()) {
                if (v.getRole() == Role.VERIFYER){
                    allVerifiers.add((User) v);
                }
            }
            return new ResponseEntity<>(allVerifiers, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not system administrator!"), HttpStatus.BAD_REQUEST);
    }

    /**
     * This method deletes user with type Verifier
     * @param username
     * @param token
     * @return ResponseEntity with HttpStatus Ok if everything is OK or BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/verifier/erase/{username}", method = RequestMethod.GET)
    public ResponseEntity erase(@PathVariable String username, @RequestHeader("X-Auth-Token") String token)
    {

        if(userService.findByUsername(username) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userService.findByToken(token);
        if (user.getRole() == Role.SYS_ADMIN){

            User eraseUser = userService.findByUsername(username);

            //obrisati i sve one stvari verifikatora!!!
            userService.delete(eraseUser);

            return new ResponseEntity<>(eraseUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not system administrator!"), HttpStatus.BAD_REQUEST);
    }



}
