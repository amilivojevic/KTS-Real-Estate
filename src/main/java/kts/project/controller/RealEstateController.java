package kts.project.controller;

import kts.project.controller.dto.RealEstateDTO;
import kts.project.model.*;
import kts.project.model.enumerations.HeatingType;
import kts.project.model.enumerations.RealEstateType;
import kts.project.model.enumerations.Role;
import kts.project.repository.AdvertisementRepository;
import kts.project.repository.LocationRepository;
import kts.project.repository.RealEstateRepository;
import kts.project.service.RealEstateService;
import kts.project.service.UserService;
import kts.project.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nina on 14-Jul-17.
 */

/**
 *This class represents controller for Real Estate and manages with all Real Estate
 * functionalities.
 */
@RestController
@RequestMapping("/api/realEstate")
public class RealEstateController {

    @Autowired
    private RealEstateRepository realEstateRepository;

    @Autowired
    private RealEstateService realEstateService;

    @Autowired
    private UserService userService;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    //TESTING COMPLETED

    /**
     * This method represents adding of a new Real Estate
     * @param token
     * @param realEstateDTO
     * @return ResponseEntity with HttpStatus CREATED if everything is OK, BAD_REQUEST if not OK
     */
    @RequestMapping(value = "/addNewRealEstate", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addNewRealEstate(@RequestHeader("X-Auth-Token") String token,@RequestBody RealEstateDTO realEstateDTO) {


        if (!realEstateService.checkRealEstateDTOInput(realEstateDTO)){
            return new ResponseEntity<>(new ResponseMessage("New Real Estate input is not valid (some fields are null)"), HttpStatus.BAD_REQUEST);
        }

            RealEstate rs = new RealEstate();

            rs.setDescription(realEstateDTO.getDescription());
            rs.setImageUrl(realEstateDTO.getImageUrl());
            rs.setFurniture(realEstateDTO.isFurniture());
            rs.setParking(realEstateDTO.isParking());
            rs.setArea(realEstateDTO.getArea());
            rs.setConstructionYear(realEstateDTO.getConstructionYear());
            rs.setRoomsNumber(realEstateDTO.getRoomsNumber());
            rs.setBathroomsNumber(realEstateDTO.getBathroomsNumber());

            Location location = new Location();
            location.setCity(realEstateDTO.getCity());
            location.setCityArea(realEstateDTO.getCityArea());
            location.setStreet(realEstateDTO.getStreet());
            location.setStreetNumber(realEstateDTO.getStreetNumber());
            location.setState(realEstateDTO.getState());
            location.setZipCode(realEstateDTO.getZipCode());
            locationRepository.save(location);
            rs.setAddress(location);
            rs.setOwner((Owner) userService.findByToken(token));

            HeatingType heatingType;
            switch (realEstateDTO.getHeatingType()){
                case "None":
                    heatingType = HeatingType.NONE;
                    break;
                case "Central":
                    heatingType = HeatingType.CENTRAL;
                    break;
                case "Fireplace":
                    heatingType = HeatingType.FIREPLACE;
                    break;
                case "Solar":
                    heatingType = HeatingType.SOLAR;
                    break;
                default:
                    heatingType = HeatingType.NONE;
                    break;
            }

            rs.setHeatingType(heatingType);

            RealEstateType realEstateType;
        switch (realEstateDTO.getRs_type()){
            case "Apartment":
                realEstateType = RealEstateType.APARTMENT;
                break;
            case "House":
                realEstateType = RealEstateType.HOUSE;
                break;
            case "Office":
                realEstateType = RealEstateType.OFFICE;
                break;
            case "Garage":
                realEstateType = RealEstateType.GARAGE;
                break;
            default:
                realEstateType = RealEstateType.APARTMENT;
                break;

        }
        rs.setRs_type(realEstateType);

            realEstateRepository.save(rs);

            return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }



    @RequestMapping(value = "/getAllMyRealEstates", method = RequestMethod.GET)
    public ResponseEntity getAllMyRealEstates(@RequestHeader("X-Auth-Token") String token)
    {

        User user = userService.findByToken(token);
        if (user.getRole() == Role.OWNER){

            List<RealEstate> allMyRealEstates = new ArrayList<>();

            for (RealEstate o : realEstateRepository.findAll()) {
                if (o.getOwner().getId() == user.getId()){
                    allMyRealEstates.add(o);
                }
            }
            return new ResponseEntity<>(allMyRealEstates, HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseMessage("Logged User is not an owner!"), HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "/erase/{id}", method = RequestMethod.GET)
    public ResponseEntity erase(@PathVariable Long id, @RequestHeader("X-Auth-Token") String token)
    {
        User user = userService.findByToken(token);


        RealEstate re = realEstateRepository.findById(id);

        if (re.getOwner().getId() == user.getId()){


            for (Advertisement a : advertisementRepository.findAll()) {
                if(a.getRealEstate().getId() == id){
                    advertisementRepository.delete(a.getId());
                }
            }
            realEstateRepository.delete(re);

            //treba obrisati i sve one iste!!!!
            return new ResponseEntity<>(re, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("You are not allowed to delete this real estate!"), HttpStatus.OK);

    }

}
