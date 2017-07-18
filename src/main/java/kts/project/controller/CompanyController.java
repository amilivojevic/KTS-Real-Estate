package kts.project.controller;

import kts.project.controller.dto.ApprovedCompanyDTO;
import kts.project.controller.dto.RegisterCompanyDTO;
import kts.project.model.Company;
import kts.project.model.User;
import kts.project.model.enumerations.Role;
import kts.project.security.UserUtils;
import kts.project.service.AuthorityService;
import kts.project.service.CompanyService;
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
    AuthorityService authorityService;

    @Autowired
    UserService userService;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private CompanyService companyService;


    //registracija obicnih korisnika!
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveUser(@RequestBody RegisterCompanyDTO registerCompanyDTO) {

        if(!userUtils.checkUniqueUsername(registerCompanyDTO.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username is not unique!");
        }
        if(!companyService.checkUniquePIB(registerCompanyDTO.getPib())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("PIB is not unique!");
        }
        if(registerCompanyDTO.getUsername() == null || registerCompanyDTO.getEmail() == null || registerCompanyDTO.getPassword() == null
                || registerCompanyDTO.getPib() == null){
            return new ResponseEntity<>(new ResponseMessage("Username should not be null!"), HttpStatus.BAD_REQUEST);
        }

        if(registerCompanyDTO.getUsername().equals("") || registerCompanyDTO.getEmail().equals("") || registerCompanyDTO.getPassword().equals("")
                || registerCompanyDTO.getPib().equals("")){
            return new ResponseEntity<>(new ResponseMessage("Username should not be null!"), HttpStatus.BAD_REQUEST);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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

            company.setAuthority(authorityService.findByName(("ROLE_OWNER")));
        }
        else {
            System.out.println("Nije Owner ili company");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Cant create that type of user, ony Customer and Advertiser allowed");
        }

        companyService.save(company);

        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getAllCompanies() {
        return new ResponseEntity(companyService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/all/unapproved", method = RequestMethod.GET)
    public ResponseEntity getAllUnapprovedCompanies() {
        List<Company> unapprovedCompanies = new ArrayList<>();
        for (Company c: companyService.findAll()) {
            if (c.isApproved() == false)
                unapprovedCompanies.add(c);
        }
        return new ResponseEntity<>(unapprovedCompanies, HttpStatus.OK);
    }



    @RequestMapping(value = "/approve/{username}", method = RequestMethod.GET)
    public ResponseEntity approve(@PathVariable String username, @RequestHeader("X-Auth-Token") String token) {

        User user = userService.findByToken(token);
        if (user.getRole() == Role.SYS_ADMIN) {

            User eraseUser = userService.findByUsername(username);
            Company c = companyService.findByUsername(username);
     //       System.out.println("Nasao je kompaniju u bazi : " + c.toString());

            c.setApproved(true);
            companyService.save(c);

            return new ResponseEntity<>(new ApprovedCompanyDTO(c), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not system administrator!"), HttpStatus.OK);
    }

}
