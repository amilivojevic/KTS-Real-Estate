package kts.project.controller;

import kts.project.controller.dto.AddAdvertisementDTO;
import kts.project.model.Advertisement;
import kts.project.model.Owner;
import kts.project.model.Review;
import kts.project.model.enumerations.AdvertisementState;
import kts.project.model.enumerations.AdvertisementType;
import kts.project.model.enumerations.Currency;
import kts.project.repository.AdvertisementRepository;
import kts.project.repository.RealEstateRepository;
import kts.project.service.AdvertisementService;
import kts.project.service.UserService;
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

@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    UserService userService;

    @Autowired
    RealEstateRepository realEstateRepository;

    //Adding new advertisement
    @RequestMapping(value = "/addNewAdvertisement", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addNewAdvertisement(@RequestHeader("X-Auth-Token") String token, @RequestBody AddAdvertisementDTO addAdvertisementDTO) {


        Advertisement ad = new Advertisement();

        ad.setTitle(addAdvertisementDTO.getTitle());
        ad.setPrice(addAdvertisementDTO.getPrice());
        ad.setEndingDate(addAdvertisementDTO.getEndingDate());
        ad.setAnnouncementDate(new Date());
        ad.setUpdateDate(new Date());
        ad.setPhoneNumber(addAdvertisementDTO.getPhoneNumber());
        ad.setReviews(new ArrayList<>());


        ad.setState(AdvertisementState.WAITING);

        if (realEstateRepository.findById(addAdvertisementDTO.getId()) != null){
            System.out.println("real estate nije null");
            ad.setRealEstate(realEstateRepository.findById(addAdvertisementDTO.getId()));
        }
        else{
            System.out.println("real estate NULL");
        }



        AdvertisementType type;

        switch (addAdvertisementDTO.getType()){
            case "Sale":
                type = AdvertisementType.SALE;
                break;
            case "Rent":
                type = AdvertisementType.RENT;
                break;
            default:
                type = AdvertisementType.SALE;
                break;
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
                currency = Currency.EUR;
                break;

        }
        ad.setCurrency(currency);

        System.out.println("mrrrrssssssssssss");
        ad.setOwner((Owner) userService.findByToken(token));

        System.out.println("mrrrrssssssssssss 2");
        advertisementRepository.save(ad);

        System.out.println("mrrrrssssssssssss 3");
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }


}
