package kts.project.controller;

import kts.project.controller.dto.RegisterCompanyDTO;
import kts.project.controller.dto.RegisterOwnerDTO;
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
@RequestMapping("/api/users/company")
public class CompanyController {


    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserService userService;


    //registracija obicnih korisnika!
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveUser(@RequestBody RegisterCompanyDTO registerCompanyDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Pocinje registracija ownera na backendu!");

        System.out.println("COMPANY: " + registerCompanyDTO.toString());

        Company company;

        if (registerCompanyDTO.getRole().equalsIgnoreCase("OWNER") &&
                registerCompanyDTO.getType().equalsIgnoreCase("COMPANY")) {

            company = new Company();
            company.setRole(Role.OWNER);
            company.setApproved(false);
            company.setFax(registerCompanyDTO.getFax());
            company.setPib(registerCompanyDTO.getPib());
            company.setSite(registerCompanyDTO.getSite());
            company.setAccountNumber(registerCompanyDTO.getAccountNumber());
            company.setAddress(registerCompanyDTO.getAddress());
            company.setBirthDate(registerCompanyDTO.getBirthDate());
            company.setCity(registerCompanyDTO.getCity());
            company.setCountry(registerCompanyDTO.getCountry());
            company.setName(registerCompanyDTO.getName());
            company.setSurname(registerCompanyDTO.getSurname());
            company.setUsername(registerCompanyDTO.getUsername());
            company.setEmail(registerCompanyDTO.getEmail());
            company.setPassword(encoder.encode(registerCompanyDTO.getPassword()));
            company.setPhoneNumber(registerCompanyDTO.getPhoneNumber());
            company.setImageUrl(registerCompanyDTO.getImageUrl());
            //
            company.setWorkers(new ArrayList<>());
            company.setAdvertisements(new ArrayList<>());

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

    @RequestMapping(value = "/all/unapproved", method = RequestMethod.GET)
    public ResponseEntity getAllUnapprovedCompanies() {
        List<Company> unapprovedCompanies = new ArrayList<>();
        for (Company c: companyRepository.findAll()) {
            if (c.isApproved() == false)
                unapprovedCompanies.add(c);
        }
        return new ResponseEntity<>(unapprovedCompanies, HttpStatus.OK);
    }



    @RequestMapping(value = "/approve/{username}", method = RequestMethod.GET)
    public ResponseEntity approve(@PathVariable String username, @RequestHeader("X-Auth-Token") String token) {

        User user = userService.findByToken(token);
        if (user.getRole() == Role.SYS_ADMIN) {

            User eraseUser = userRepository.findByUsername(username);
            Company c = companyRepository.findByUsername(username);
            System.out.println("Nasao je kompaniju u bazi : " + c.toString());

            c.setApproved(true);
            companyRepository.save(c);

            return new ResponseEntity<>(eraseUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not system administrator!"), HttpStatus.OK);
    }

}
