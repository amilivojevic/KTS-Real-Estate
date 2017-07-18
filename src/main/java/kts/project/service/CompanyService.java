package kts.project.service;

import kts.project.model.Company;
import kts.project.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin.com.event.COMEventHandler;

import java.util.List;

/**
 * This class represents Real Estate Service
 *
 */
@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    /**
     * This method is finding one Company by its Id
     * @param id
     * @return Company with specified id
     */
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

    /**
     * This method is getting one Company by its Id
     * @param id
     * @return Company with specified id
     */
    public Company getOne(Long id) {return companyRepository.getOne(id);}

    /**
     * This method is finding one Company by its Id
     * @param id
     * @return Company with specified id
     */
    public Company findOne(Long id) {return companyRepository.findOne(id);}

}
