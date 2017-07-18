package kts.project.service;

import kts.project.controller.dto.VerifierReportDTO;
import kts.project.model.VerifierReport;
import kts.project.repository.VerifierReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nina on 16-Jul-17.
 */

/**
 * This class represents VerifyReportService
 *
 */
@Service
public class VerifierReportService {

    @Autowired
    VerifierReportRepository verifierReportRepository;

    /**
     * This method finds element with specified id and gets it from the
     * database.
     *
     * @param id
     *            Element id
     * @return Element if found, null if doesn't exist
     */
    public VerifierReport findById(Long id){
        return verifierReportRepository.findById(id);
    }

    /**
     * This method saves element to the database.
     *
     * @param u
     *            element to be saved
     * @return Saved element
     */
    public VerifierReport save(VerifierReport u){
        return verifierReportRepository.save(u);
    }

    /**
     * This method is checking if all required inputs for VerifierReportDTO are entered
     * @param verifierReportDTO
     * @return true or false
     */
    public boolean checkVerifyReportDTOInput(VerifierReportDTO verifierReportDTO){
        if (verifierReportDTO.getDescription().equals("") ||
                verifierReportDTO.getDate() == null ||
                verifierReportDTO.getBanningReason().equals("")){
            return false;
        }
        else{
            return true;
        }
    }
}
