package kts.project.controller;

import kts.project.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by USER on 6/11/2017.
 */

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getAllLocations() {
        return new ResponseEntity<>(locationRepository.findAll(), HttpStatus.OK);
    }

}
