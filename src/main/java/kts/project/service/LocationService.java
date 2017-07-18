package kts.project.service;

import kts.project.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class represents Location Service
 *
 */
@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;
}
