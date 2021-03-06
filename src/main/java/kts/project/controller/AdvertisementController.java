package kts.project.controller;

import kts.project.controller.dto.AddAdvertisementDTO;
import kts.project.controller.dto.FilterAdvertisementDTO;
import kts.project.model.Advertisement;
import kts.project.model.Owner;
import kts.project.model.User;
import kts.project.model.enumerations.AdvertisementState;
import kts.project.model.enumerations.AdvertisementType;
import kts.project.model.enumerations.Currency;
import kts.project.model.enumerations.Role;
import kts.project.service.AdvertisementService;
import kts.project.service.RealEstateService;
import kts.project.service.UserService;
import kts.project.service.VerifierReportService;
import kts.project.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  This controller represents Advertisement and all its functionality
 */
@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    UserService userService;

    @Autowired
    RealEstateService realEstateService;

    @Autowired
    VerifierReportService verifierReportService;

    //Adding new advertisement

    /**
     *  This method creates new advertisement in Database
     * @param token
     * @param addAdvertisementDTO
     * @return ResponseEntity with HttpStatus CREATED if everything is OK or BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/addNewAdvertisement", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addNewAdvertisement(@RequestHeader("X-Auth-Token") String token, @RequestBody AddAdvertisementDTO addAdvertisementDTO) {
        if (!advertisementService.checkAdvertisementDTOInput(addAdvertisementDTO)){
            return new ResponseEntity<>(new ResponseMessage("New Advertisement input is not valid (some fields are null)"), HttpStatus.BAD_REQUEST);
        }

        if(realEstateService.findById(addAdvertisementDTO.getId()) == null){
            return new ResponseEntity<>(new ResponseMessage("Real Estate with id: " + addAdvertisementDTO.getId() + "doesn't exist"), HttpStatus.NOT_FOUND);

        }

        Advertisement ad = new Advertisement();

        ad.setTitle(addAdvertisementDTO.getTitle());
        ad.setPrice(addAdvertisementDTO.getPrice());
        ad.setEndingDate(addAdvertisementDTO.getEndingDate());
        ad.setAnnouncementDate(new Date());
        ad.setUpdateDate(new Date());
        ad.setPhoneNumber(addAdvertisementDTO.getPhoneNumber());
        ad.setReviews(new ArrayList<>());
        ad.setState(AdvertisementState.WAITING);

        ad.setRealEstate(realEstateService.findById(addAdvertisementDTO.getId()));

        AdvertisementType type;

        switch (addAdvertisementDTO.getType().toUpperCase()){
            case "SELL":
                type = AdvertisementType.SELL;
                break;
            case "RENT":
                type = AdvertisementType.RENT;
                break;
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

        ad.setType(type);

        Currency currency;
        switch (addAdvertisementDTO.getCurrency()){
            case "EUR":
                currency = Currency.EUR;
                break;
            case "RSD":
                currency = Currency.RSD;
                break;
            case "USD":
                currency = Currency.USD;
                break;

            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        ad.setCurrency(currency);

        ad.setOwner((Owner) userService.findByToken(token));
        advertisementService.save(ad);

        return new ResponseEntity<>(ad, HttpStatus.CREATED);

    }


    /**
     * This method is getting all Advertisements from the database
     * @return ResponseEntity with HttpStatus OK if everything is OK
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAllAdvertisements() {

        List<Advertisement> allAdvertisements = new ArrayList<>();

        for (Advertisement o : advertisementService.findAll()) {
            allAdvertisements.add(o);
        }

        return new ResponseEntity<>(allAdvertisements, HttpStatus.OK);
    }

    /**
     * This method is getting all Accepted Listings from database
     * @return ResponseEntity with HttpStatus OK if everything is OK
     */
    @RequestMapping(value = "/getAllAcceptedListings", method = RequestMethod.GET)
    public ResponseEntity getAllAcceptedAdvertisements()
    {

        List<Advertisement> allAdvertisements = new ArrayList<>();

        for (Advertisement o : advertisementService.findAll()) {
            if(o.getState() == AdvertisementState.ACCEPTED) {
                allAdvertisements.add(o);
            }
        }

        return new ResponseEntity<>(allAdvertisements, HttpStatus.OK);

    }

    /**
     * This method change state of specified Advertisement on ACCEPTED
     * @return ResponseEntity with HttpStatus OK if everything is OK
     * BAD_REQUEST if someone who is not a verifier is trying to accept adv.
     */
    @RequestMapping(value = "/accept/{id}", method = RequestMethod.GET)
    public ResponseEntity acceptAdvertisements(@PathVariable Long id, @RequestHeader("X-Auth-Token") String token)
    {
        System.out.println("acceptAdvertisements");
        User user = userService.findByToken(token);
        if(user.getRole() == Role.VERIFYER){
            Advertisement a = advertisementService.findById(id);
            a.setState(AdvertisementState.ACCEPTED);
            advertisementService.save(a);
            return new ResponseEntity<>( HttpStatus.OK);
        }

        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }


    /**
     * This method change state of specified Advertisement on EXIPERD (someone bought real estate)
     * @return ResponseEntity with HttpStatus OK if everything is OK
     */
    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public ResponseEntity buyAdvertisements(@PathVariable Long id, @RequestHeader("X-Auth-Token") String token)
    {

            Advertisement a = advertisementService.findById(id);
            a.setState(AdvertisementState.EXPIRED);
            advertisementService.save(a);
            return new ResponseEntity<>( HttpStatus.OK);

    }

    /**
     * This method is getting all Waiting Advertisements from database
     * @return ResponseEntity with HttpStatus OK if everything is OK
     */
    @RequestMapping(value = "/getAllWaiting", method = RequestMethod.GET)
    public ResponseEntity getAllWaitingAdvertisements()
    {

        List<Advertisement> allWaitingAdvertisements = new ArrayList<>();

        for (Advertisement o : advertisementService.findAll()) {
            if(o.getState() == AdvertisementState.WAITING) {
                allWaitingAdvertisements.add(o);
            }

        }

        return new ResponseEntity<>(allWaitingAdvertisements, HttpStatus.OK);

    }

    /**
     * This method is getting one single Advertisement from database by its id
     * @return ResponseEntity with HttpStatus OK if everything is OK or BAD_REQUEST if not OK, else NOT_FOUND
     */
    @RequestMapping(value = "/getSingleAdvertisement/{id}", method = RequestMethod.GET)
    public ResponseEntity getSingleAdvertisement(@PathVariable Long id)
    {
        if(id == null){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

        if(advertisementService.findById(id) == null){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }

        Advertisement singleAdvertisement = advertisementService.findById(id);
        System.out.println("ADVERTISEMENT ID : " + singleAdvertisement.getId());
        return new ResponseEntity<>(singleAdvertisement, HttpStatus.OK);

    }

    /**
     * This method is doing physical deleting of one Advertisement from database
     * @param id
     * @param token
     * @return ResponseEntity with HttpStatus OK if everything is OK or BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/erase/{id}", method = RequestMethod.GET)
    public ResponseEntity erase(@PathVariable Long id, @RequestHeader("X-Auth-Token") String token)
    {

        User user = userService.findByToken(token);
        if(user.getRole() == Role.SYS_ADMIN || user.getRole() == Role.VERIFYER){
            return new ResponseEntity<>(new ResponseMessage("You are not allowed to delete advertisement!"), HttpStatus.BAD_REQUEST);
        }
        Advertisement a = advertisementService.findById(id);
        if (a.getOwner().getId() == user.getId()){

            verifierReportService.deleteReportsOfAdvertisement(a);
            advertisementService.delete(a);

            //treba obrisati i sve one iste!!!!
            return new ResponseEntity<>(a, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not allowed to delete advertisement!"), HttpStatus.BAD_REQUEST);

    }

    /**
     * This method is used for filtering all Advertisements
     * @param filterAdvertisementDTO
     * @return ResponseEntity with HttpStatus OK if everything is OK
     */
    @RequestMapping(value = "/filterListings", method = RequestMethod.POST)
    public ResponseEntity filterListings(@RequestBody FilterAdvertisementDTO filterAdvertisementDTO)
    {

        List<Advertisement> filteredAdvertisements = new ArrayList<>();
        System.out.println(filterAdvertisementDTO);

        AdvertisementType aTypeDTO = advertisementService.getAdvertisementFromString(filterAdvertisementDTO.getType());
        Currency aCurrencyDTO = advertisementService.getCurrencyFromString(filterAdvertisementDTO.getCurrency());
        filteredAdvertisements = advertisementService.findAll();

        filteredAdvertisements = advertisementService.typeFilter(filteredAdvertisements,aTypeDTO);
        filteredAdvertisements = advertisementService.priceFilter(filteredAdvertisements,filterAdvertisementDTO.getMinPrice(),filterAdvertisementDTO.getMaxPrice(),aCurrencyDTO);

        return new ResponseEntity<>(filteredAdvertisements, HttpStatus.OK);

    }

}


