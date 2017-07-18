package kts.project.controller;

import kts.project.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *This class represents controller for Location and manages with all Location
 * functionalities.
 */
@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    /**
     * This method gets all Locations
     * @return ResponseEntity with HttpStatus OK if everything is OK
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getAllLocations() {
        return new ResponseEntity<>(locationRepository.findAll(), HttpStatus.OK);
    }

}
