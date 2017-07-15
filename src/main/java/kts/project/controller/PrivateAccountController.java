package kts.project.controller;

import kts.project.controller.dto.RegisterOwnerDTO;
import kts.project.controller.dto.RegisterPrivateAccDTO;
import kts.project.model.Company;
import kts.project.model.Owner;
import kts.project.model.PrivateAccountInCompany;
import kts.project.model.User;
import kts.project.model.enumerations.Role;
import kts.project.repository.*;
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
 * Created by Sandra on 6/12/2017.
 */
@RestController
@RequestMapping("/api/users/private_acc")
public class PrivateAccountController {

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    PrivateAccountInCompanyRepository privateAccountInCompanyRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserService userService;


    //registracija obicnih korisnika!
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity savePrivateAccount(@RequestBody RegisterPrivateAccDTO registerPrivateAccDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Pocinje registracija privatnog lica na backendu!");

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
            user.setAuthority(authorityRepository.findByName(("ROLE_OWNER")));
            privateAcc.setOwner(user);
            privateAcc.setApproved(false);
            privateAcc.setCompany(companyRepository.findOne(registerPrivateAccDTO.getCompanyId()));
        }
        else {
            System.out.println();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant create that type of user, ony Customer and Advertiser allowed");
        }

        //user.setVerifyCode(UUID.randomUUID().toString());

        ownerRepository.save(user);
        privateAccountInCompanyRepository.save(privateAcc);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //dobavljaju se samo unapproved private accounts od companije koja je ulogovana
    @RequestMapping(value = "/all/unapproved", method = RequestMethod.GET)
    public ResponseEntity getAllUnapproved(@RequestHeader("X-Auth-Token") String token) {
        List<PrivateAccountInCompany> unapprovedPrivate = new ArrayList<>();
        User user = userService.findByToken(token);
        if (user.getRole() == Role.OWNER) {
            if(companyRepository.getOne(user.getId()) != null){
                for (PrivateAccountInCompany c: privateAccountInCompanyRepository.findAll()) {

                    if(c.getCompany().getId() == user.getId()) {
                        if (c.isApproved() == false) {
                            unapprovedPrivate.add(c);
                        }
                    }
                }
            }
        }


        return new ResponseEntity<>(unapprovedPrivate, HttpStatus.OK);
    }


    @RequestMapping(value = "/approve/{id}", method = RequestMethod.GET)
    public ResponseEntity approve(@PathVariable long id, @RequestHeader("X-Auth-Token") String token) {

        User user = userService.findByToken(token);
        System.out.println("Funkcija approve, user.getId() = " + user.getId());
        if (user.getRole() == Role.OWNER) {
            System.out.println("user je owner");
            if(companyRepository.getOne(user.getId()) != null){

                PrivateAccountInCompany p = privateAccountInCompanyRepository.getOne(id);

                System.out.println("Nasao je privateaccount u bazi : " + p.toString());

                p.setApproved(true);
                System.out.println("postavio na approved");
                privateAccountInCompanyRepository.save(p);
                System.out.println("zapamtio u bazi");
                return new ResponseEntity<>(new ResponseMessage("Private acccount in company approved!"), HttpStatus.OK);
            }


        }
        return new ResponseEntity<>(new ResponseMessage("You are not system administrator!"), HttpStatus.OK);
    }
}
