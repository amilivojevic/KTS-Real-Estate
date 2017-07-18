package kts.project.controller;

import kts.project.controller.dto.RegisterOwnerDTO;
import kts.project.controller.dto.RegisterPrivateAccDTO;
import kts.project.model.Company;
import kts.project.model.Owner;
import kts.project.model.PrivateAccountInCompany;
import kts.project.model.User;
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
 *This class represents controller for Private Account in Company and manages with all Real Estate
 * functionalities.
 */
@RestController
@RequestMapping("/api/users/private_acc")
public class PrivateAccountController {

    @Autowired
    OwnerService ownerService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    PrivateAccountInCompanyService privateAccountInCompanyService;

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;


    /**
     * This method represents registration of Private Acount inside Company
     * @param registerPrivateAccDTO
     * @return ResponseEntity with HttpStatus OK if everything is OK or BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity savePrivateAccount(@RequestBody RegisterPrivateAccDTO registerPrivateAccDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Pocinje registracija privatnog lica na backendu!");

        if (!privateAccountInCompanyService.checkPrivateAccountInCompanyDTOInput(registerPrivateAccDTO)){
            return new ResponseEntity<>(new ResponseMessage("New Private Account in Company input is not valid (some fields are null)"), HttpStatus.BAD_REQUEST);
        }

        Owner user;
        PrivateAccountInCompany privateAcc = new PrivateAccountInCompany();

        if (registerPrivateAccDTO.getRole().equalsIgnoreCase("OWNER")) {

            user = new Owner(
                    registerPrivateAccDTO.getUsername(),
                    encoder.encode(registerPrivateAccDTO.getPassword()),
                    registerPrivateAccDTO.getEmail(),
                    Role.OWNER,
                    registerPrivateAccDTO.getName(),
                    registerPrivateAccDTO.getSurname(),
                    registerPrivateAccDTO.getBirthDate(),
                    registerPrivateAccDTO.getPhoneNumber(),
                    registerPrivateAccDTO.getAddress(),
                    registerPrivateAccDTO.getCity(),
                    registerPrivateAccDTO.getCountry(),
                    registerPrivateAccDTO.getAccountNumber(),
                    registerPrivateAccDTO.getImageUrl()
            );
            user.setAuthority(authorityService.findByName(("ROLE_OWNER")));
            privateAcc.setOwner(user);
            privateAcc.setApproved(false);
            privateAcc.setCompany(companyService.findOne(registerPrivateAccDTO.getCompanyId()));
        }
        else {
            System.out.println();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant create that type of user, ony Customer and Advertiser allowed");
        }
        ownerService.save(user);
        privateAccountInCompanyService.save(privateAcc);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * This method gets all Private Accounts in Company with status Unapproved
     * @param token
     * @return ResponseEntity with HttpStatus OK if everything is OK or BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/all/unapproved", method = RequestMethod.GET)
    public ResponseEntity getAllUnapproved(@RequestHeader("X-Auth-Token") String token) {
        List<PrivateAccountInCompany> unapprovedPrivate = new ArrayList<>();
        User user = userService.findByToken(token);
        if (user.getRole() == Role.OWNER) {
            if (companyService.getOne(user.getId()) != null) {
                for (PrivateAccountInCompany c : privateAccountInCompanyService.findAll()) {

                    if (c.getCompany().getId() == user.getId()) {
                        if (c.isApproved() == false) {
                            unapprovedPrivate.add(c);
                        }
                    }
                }
            }

            return new ResponseEntity<>(unapprovedPrivate, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(unapprovedPrivate, HttpStatus.BAD_REQUEST);
        }
    }


    // TESTING IN PROGRESS

    /**
     * This method is gets one unapproved Private accounts in Company in addition to change its status to approved
     * @param id
     * @param token
     * @return ResponseEntity with HttpStatus OK if everything is OK or BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/approve/{id}", method = RequestMethod.GET)
    public ResponseEntity approve(@PathVariable long id, @RequestHeader("X-Auth-Token") String token) {

        User user = userService.findByToken(token);
        System.out.println("Funkcija approve, user.getId() = " + user.getId());
        if (user.getRole() == Role.OWNER) {
            System.out.println("user je owner");
            if(companyService.getOne(user.getId()) != null){

                PrivateAccountInCompany p = privateAccountInCompanyService.findById(id);

                System.out.println("Nasao je privateaccount u bazi : " + p.toString());

                p.setApproved(true);
                System.out.println("postavio na approved");
                privateAccountInCompanyService.save(p);
                System.out.println("zapamtio u bazi");
                return new ResponseEntity<>( HttpStatus.OK);
            }


        }
        return new ResponseEntity( HttpStatus.BAD_REQUEST);
    }
}
