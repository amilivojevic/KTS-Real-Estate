package kts.project.controller;

import kts.project.controller.dto.RegisterOwnerDTO;
import kts.project.controller.dto.RegisterPrivateAccDTO;
import kts.project.model.Company;
import kts.project.model.Owner;
import kts.project.model.PrivateAccountInCompany;
import kts.project.model.enumerations.Role;
import kts.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
