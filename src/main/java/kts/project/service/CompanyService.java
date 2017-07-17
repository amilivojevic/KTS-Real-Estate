package kts.project.service;

import kts.project.model.Company;
import kts.project.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nina on 17-Jul-17.
 */

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public Company findById(Long id){
        return companyRepository.findById(id);
    }

}
