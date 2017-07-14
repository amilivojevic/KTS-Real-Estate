package kts.project.controller;

import kts.project.controller.dto.RegisterCompanyDTO;
import kts.project.controller.dto.RegisterOwnerDTO;
import kts.project.model.Company;
import kts.project.model.Owner;
import kts.project.model.PrivateAccountInCompany;
import kts.project.model.enumerations.Role;
import kts.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Sandra on 6/12/2017.
 */
@RestController
@RequestMapping("/api/users/company")
public class CompanyController {


    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    PrivateAccountInCompanyRepository privateAccRepository;


    //registracija obicnih korisnika!
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveUser(@RequestBody RegisterCompanyDTO registerCompanyDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Pocinje registracija ownera na backendu!");

        System.out.println("COMPANY: " + registerCompanyDTO.toString());

        Company company;

        if (registerCompanyDTO.getRole().equalsIgnoreCase("OWNER") &&
                registerCompanyDTO.getType().equalsIgnoreCase("COMPANY")) {

            company = new Company(

            );

            company.setAuthority(authorityRepository.findByName(("ROLE_OWNER")));
        }
        else {
            System.out.println("Nije Owner ili company");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant create that type of user, ony Customer and Advertiser allowed");
        }

        //ownerRepository.save(company);
        companyRepository.save(company);
        //userRepository.save(company)

        return new ResponseEntity<>(company, HttpStatus.OK);
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getAllCompanies() {
        return new ResponseEntity<>(companyRepository.findAll(), HttpStatus.OK);
    }


}
