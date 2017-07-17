package kts.project.controller;

import kts.project.controller.dto.AddAdvertisementDTO;
import kts.project.controller.dto.FilterAdvertisementDTO;
import kts.project.model.Advertisement;
import kts.project.model.Owner;
import kts.project.model.Review;
import kts.project.model.User;
import kts.project.model.enumerations.AdvertisementState;
import kts.project.model.enumerations.AdvertisementType;
import kts.project.model.enumerations.Currency;
import kts.project.service.AdvertisementService;
import kts.project.service.RealEstateService;
import kts.project.service.UserService;
import kts.project.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nina on 14-Jul-17.
 */


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

    //Adding new advertisement

    /**
     *  This method creates new advertisement in Database
     *
     * @param token
     * @param addAdvertisementDTO
     * @return ResponseEntity with HttpStatus CREATED if everything is OK or BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/addNewAdvertisement", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addNewAdvertisement(@RequestHeader("X-Auth-Token") String token, @RequestBody AddAdvertisementDTO addAdvertisementDTO) {

        if (!checkAdvertisementDTOInput(addAdvertisementDTO)){
            return new ResponseEntity<>(new ResponseMessage("New Advertisement input is not valid (some fields are null)"), HttpStatus.BAD_REQUEST);
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

        if (realEstateService.findById(addAdvertisementDTO.getId()) != null){
            System.out.println("real estate nije null");
            ad.setRealEstate(realEstateService.findById(addAdvertisementDTO.getId()));
        }
        else{
            System.out.println("real estate NULL");
        }


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

    private boolean checkAdvertisementDTOInput(AddAdvertisementDTO addAdvertisementDTO){
        if (addAdvertisementDTO.getCurrency().equals("") ||
                addAdvertisementDTO.getEndingDate() == null ||
                addAdvertisementDTO.getPhoneNumber().equals("") ||
                addAdvertisementDTO.getTitle().equals("") ||
                addAdvertisementDTO.getType() == null){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAllAdvertisements() {

        List<Advertisement> allAdvertisements = new ArrayList<>();

        for (Advertisement o : advertisementService.findAll()) {

            allAdvertisements.add(o);

        }

        return new ResponseEntity<>(allAdvertisements, HttpStatus.OK);
    }

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

    @RequestMapping(value = "/getSingleAdvertisement/{id}", method = RequestMethod.GET)
    public ResponseEntity getSingleAdvertisement(@PathVariable Long id)
    {

        Advertisement singleAdvertisement = advertisementService.findById(id);

        return new ResponseEntity<>(singleAdvertisement, HttpStatus.OK);

    }

    @RequestMapping(value = "/erase/{id}", method = RequestMethod.GET)
    public ResponseEntity erase(@PathVariable Long id, @RequestHeader("X-Auth-Token") String token)
    {
        User user = userService.findByToken(token);
        Advertisement a = advertisementService.findById(id);
        if (a.getOwner().getId() == user.getId()){

            advertisementService.delete(a);

            //treba obrisati i sve one iste!!!!
            return new ResponseEntity<>(a, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not allowed to delete advertisement!"), HttpStatus.OK);

    }


    @RequestMapping(value = "/filterListings", method = RequestMethod.POST)
    public ResponseEntity filterListings(@RequestBody FilterAdvertisementDTO filterAdvertisementDTO)
    {

        List<Advertisement> filteredAdvertisements = new ArrayList<>();
        System.out.println(filterAdvertisementDTO);

        AdvertisementType aTypeDTO = getAdvertisementFromString(filterAdvertisementDTO.getType());
        Currency aCurrencyDTO = getCurrencyFromString(filterAdvertisementDTO.getCurrency());
        filteredAdvertisements = advertisementService.findAll();

        filteredAdvertisements = typeFilter(filteredAdvertisements,aTypeDTO);
        filteredAdvertisements = priceFilter(filteredAdvertisements,filterAdvertisementDTO.getMinPrice(),filterAdvertisementDTO.getMaxPrice(),aCurrencyDTO);

        return new ResponseEntity<>(filteredAdvertisements, HttpStatus.OK);

    }


    private AdvertisementType getAdvertisementFromString(String stringType){
        if(stringType == null){
            System.out.println("tip je null");
            return null;
        }
        if(stringType.equalsIgnoreCase("RENT")){
            System.out.println("tip je rent");
            return AdvertisementType.RENT;
        }
        else if(stringType.equalsIgnoreCase("SELL")){
            System.out.println("tip je sell");
            return AdvertisementType.SELL;
        }
        return null;
    }

    private Currency getCurrencyFromString(String stringCurrency){
        if(stringCurrency == null){
            System.out.println("Currency je null");
            return null;
        }
        if(stringCurrency.equalsIgnoreCase("EUR")){
            System.out.println("Currency je EUR");
            return Currency.EUR;
        }
        else if(stringCurrency.equalsIgnoreCase("RSD")){
            System.out.println("Currency je RSD");
            return Currency.RSD;
        }
        else if(stringCurrency.equalsIgnoreCase("USD")){
            System.out.println("Currency je USD");
            return Currency.USD;
        }
        return null;
    }



    private List<Advertisement> typeFilter(List<Advertisement> list, AdvertisementType type){
        List<Advertisement> copy = new ArrayList<Advertisement>();
        for(Advertisement a : list){ copy.add(a);}

        for (Advertisement a : copy){

            if(type != null && type != a.getType()){

                list.remove(a);
            }
        }
        return list;
    }

    private List<Advertisement> priceFilter(List<Advertisement> list, float minPrice, float maxPrice, Currency currency){

        List<Advertisement> copy = new ArrayList<>();
        for(Advertisement a : list){ copy.add(a);}

        for (Advertisement a : copy){

            if(currency != a.getCurrency()){

                //ako je maxPrice == 0.0 onda znaci da nije nista upisano u input polje
                if(maxPrice > 0){

                    if(a.getPrice() < minPrice || a.getPrice() > maxPrice){
                        list.remove(a);
                    }

                }
                // ako nije popunuo maxPrice polje, treba proveriti samo minPrice
                else if(maxPrice == 0.0 ){
                    if(a.getPrice() < minPrice){
                        list.remove(a);
                    }
                }

            }

        }
        return list;
    }


}


