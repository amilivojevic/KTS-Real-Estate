package kts.project.service;

import kts.project.model.VerifierReport;
import kts.project.repository.VerifierReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nina on 16-Jul-17.
 */
@Service
public class VerifierReportService {

    @Autowired
    VerifierReportRepository verifierReportRepository;

    public VerifierReport findById(Long id){
        return verifierReportRepository.findById(id);
    }
}
