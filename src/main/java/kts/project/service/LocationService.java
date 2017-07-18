package kts.project.service;

import kts.project.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nina on 18-Jul-17.
 */
@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;
}
