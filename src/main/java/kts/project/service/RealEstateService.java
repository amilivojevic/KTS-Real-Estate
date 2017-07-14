package kts.project.service;

import kts.project.model.RealEstate;
import kts.project.repository.RealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nina on 14-Jul-17.
 */
@Service
public class RealEstateService {

    @Autowired
    public RealEstateRepository realEstateRepository;

    public RealEstate findById(Long id){
        return realEstateRepository.findById(id);
    }
}

