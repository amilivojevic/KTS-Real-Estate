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
import kts.project.service.AdvertisementService;
import kts.project.service.UserService;
import kts.project.service.VerifierReportService;
import kts.project.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nina on 16-Jul-17.
 */

/**
 *This class represents controller which Verifier use to ban inapropriate
 * advertisements and manages with all functionalities concerning that problem.
 */
@RestController
@RequestMapping("/api/verifierReport")
public class VerifierReportController {

    @Autowired
    VerifierReportService verifierReportService;

    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    UserService userService;

    /**
     * This method represents adding of a new Verifier Report with reasons for baning Advertisement and
     * changing Advertisement state to BANNED
     * @param id
     * @param token
     * @param verifierReportDTO
     * @return ResponseEntity with HttpStatus CREATED if everything is OK, BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/reportBanedAdvertisement/{id}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity reportBanedAdvertisement(@PathVariable Long id, @RequestHeader("X-Auth-Token") String token, @RequestBody VerifierReportDTO verifierReportDTO) {

        if (!checkVerifyReportDTOInput(verifierReportDTO)){
            return new ResponseEntity<>(new ResponseMessage("New Verifier Report input is not valid (some fields are null)"), HttpStatus.BAD_REQUEST);
        }

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

        Advertisement a = advertisementService.findById(id);
        vr.setAdvertisement(a);

        vr.setVerifier(userService.findByToken(token));

        if(vr.getVerifier().getRole() == Role.VERIFYER) {
            verifierReportService.save(vr);

            Advertisement o = advertisementService.findById(id);
            o.setState(AdvertisementState.BANNED);
            advertisementService.save(o);

            return new ResponseEntity<>(vr, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not allowed to ban this advertisement!"), HttpStatus.CREATED);
    }

    /**
     * This method is checking if all required inputs for VerifierReportDTO are entered
     * @param verifierReportDTO
     * @return true or false
     */
    private boolean checkVerifyReportDTOInput(VerifierReportDTO verifierReportDTO){
        if (verifierReportDTO.getDescription().equals("") ||
                verifierReportDTO.getDate() == null ||
                verifierReportDTO.getBanningReason().equals("")){
            return false;
        }
        else{
            return true;
        }
    }
}
