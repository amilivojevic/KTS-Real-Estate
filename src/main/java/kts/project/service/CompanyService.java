package kts.project.service;

import kts.project.model.Company;
import kts.project.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean checkUniquePIB(String PIB){
        boolean retval = true;
        for(Company c : companyRepository.findAll()){
            if(c.getPib().equalsIgnoreCase(PIB)){
                retval= false;
            }
        }
        return retval;
    }

    public void save(Company c){companyRepository.save(c);}

    public List<Company> findAll(){return companyRepository.findAll();}

    public Company findByUsername(String u){return companyRepository.findByUsername(u);}
}
