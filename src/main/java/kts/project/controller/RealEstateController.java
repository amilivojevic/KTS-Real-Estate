package kts.project.controller;

import kts.project.controller.dto.RealEstateDTO;
import kts.project.model.Location;
import kts.project.model.Owner;
import kts.project.model.RealEstate;
import kts.project.model.enumerations.HeatingType;
import kts.project.model.enumerations.RealEstateType;
import kts.project.repository.LocationRepository;
import kts.project.repository.RealEstateRepository;
import kts.project.service.RealEstateService;
import kts.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nina on 14-Jul-17.
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

    //Adding new real estate
    @RequestMapping(value = "/addNewRealEstate", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addNewRealEstate(@RequestHeader("X-Auth-Token") String token,@RequestBody RealEstateDTO realEstateDTO) {


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

            return new ResponseEntity<>(rs, HttpStatus.OK);
    }

}
