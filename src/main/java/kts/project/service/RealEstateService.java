package kts.project.service;

import kts.project.controller.dto.RealEstateDTO;
import kts.project.model.RealEstate;
import kts.project.repository.RealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class represents Real Estate Service
 *
 */
@Service
public class RealEstateService {

    @Autowired
    public RealEstateRepository realEstateRepository;

    /**
     * This method is finding one Real Estate by its Id
     * @param id
     * @return RealEstate with specified id
     */
    public RealEstate findById(Long id){
        return realEstateRepository.findById(id);
    }

    /**
     * This method is checking if all required inputs for RealEstateDTO are entered
     * @param realEstateDTO
     * @return true or false
     */
    public boolean checkRealEstateDTOInput(RealEstateDTO realEstateDTO){

        if( realEstateDTO == null){
            return false;
        }
        if (realEstateDTO.getDescription().equals("") ||
                realEstateDTO.getImageUrl() == "" ||
                realEstateDTO.getArea() <= 0 ||
                realEstateDTO.getConstructionYear().equals("")||
                realEstateDTO.getRoomsNumber() < 0 ||
                realEstateDTO.getBathroomsNumber() <0 ||
                realEstateDTO.getCity().equals("")||
                realEstateDTO.getCityArea().equals("")||
                realEstateDTO.getStreet().equals("")||
                realEstateDTO.getStreetNumber().equals("")||
                realEstateDTO.getState().equals("")||
                realEstateDTO.getZipCode().equals("")||
                realEstateDTO.getHeatingType() == null ||
                realEstateDTO.getRs_type() == null){

            return false;
        }
        else{
            return true;
        }
    }

    /**
     * This method saves element to the database.
     *
     * @param u
     *            element to be saved
     * @return Saved element
     */
    public RealEstate save(RealEstate u){
        return realEstateRepository.save(u);
    }

    /**
     *  This method deletes element from database.
     *
     * @param u
     */
    public void delete(RealEstate u){
        realEstateRepository.delete(u);
    }

    /**
     * This method is finding all RealEstates
     * @return list of RealEstates
     */
    public List<RealEstate> findAll() { return  realEstateRepository.findAll();}
}

