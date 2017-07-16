package kts.project.controller;

import kts.project.controller.dto.VerifierReportDTO;
import kts.project.model.Advertisement;
import kts.project.model.Owner;
import kts.project.model.User;
import kts.project.model.VerifierReport;
import kts.project.model.enumerations.AdvertisementState;
import kts.project.model.enumerations.BanningReason;
import kts.project.model.enumerations.Role;
import kts.project.repository.AdvertisementRepository;
import kts.project.repository.RealEstateRepository;
import kts.project.repository.VerifierReportRepository;
import kts.project.service.UserService;
import kts.project.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nina on 16-Jul-17.
 */

@RestController
@RequestMapping("/api/verifierReport")
public class VerifierReportController {

    @Autowired
    VerifierReportRepository verifierReportRepository;

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    UserService userService;

    @Autowired
    RealEstateRepository realEstateRepository;

    //Adding new advertisement
    @RequestMapping(value = "/reportBanedAdvertisement/{id}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity reportBanedAdvertisement(@PathVariable Long id, @RequestHeader("X-Auth-Token") String token, @RequestBody VerifierReportDTO verifierReportDTO) {


        VerifierReport vr = new VerifierReport();

        vr.setDescription(verifierReportDTO.getDescription());
        vr.setDate(verifierReportDTO.getDate());

        BanningReason banningReason;

        switch (verifierReportDTO.getBanningReason()){
            case "Invalid image":
                banningReason = BanningReason.INVALID_IMAGE;
                break;
            case "Sold":
                banningReason = BanningReason.SOLD;
                break;
            case "Other":
                banningReason = BanningReason.OTHER;
                break;
            default:
                banningReason = BanningReason.OTHER;
                break;
        }

        vr.setBanningReason(banningReason);



        Advertisement a = advertisementRepository.findById(id);
        vr.setAdvertisement(a);
        System.out.println("Id od advertisementa kojem menjam na baned:" + id);

        vr.setVerifier(userService.findByToken(token));

        if(vr.getVerifier().getRole() == Role.VERIFYER) {
            verifierReportRepository.save(vr);

            Advertisement o = advertisementRepository.findById(id);
            o.setState(AdvertisementState.BANNED);
            advertisementRepository.save(o);

            return new ResponseEntity<>(vr, HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseMessage("You are not allowed to ban this advertisement!"), HttpStatus.OK);

    }


}
