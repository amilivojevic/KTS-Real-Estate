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
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity addNewRealEstate(@RequestBody RealEstateDTO realEstateDTO) {


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


            rs.setOwner((Owner) userService.findByToken(realEstateDTO.getToken()));
            rs.setHeatingType(HeatingType.CENTRAL);
            rs.setRs_type(RealEstateType.APARTMENT);



            realEstateRepository.save(rs);

            return new ResponseEntity<>(rs, HttpStatus.OK);
    }

}
