package kts.project.service;

import kts.project.model.Advertisement;
import kts.project.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nina on 14-Jul-17.
 */

@Service
public class AdvertisementService {

    @Autowired
    AdvertisementRepository advertisementRepository;

    public Advertisement findById(Long id){
        return advertisementRepository.findById(id);
    }

    public List<Advertisement> findAll(){ return advertisementRepository.findAll();}
}
