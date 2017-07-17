package kts.project.service;

import kts.project.model.PrivateAccountInCompany;
import kts.project.repository.PrivateAccountInCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nina on 17-Jul-17.
 */

@Service
public class PrivateAccountInCompanyService {

    @Autowired
    PrivateAccountInCompanyRepository privateAccountInCompanyRepository;


    public PrivateAccountInCompany findById(Long id){
        return privateAccountInCompanyRepository.findById(id);
    }

    public List<PrivateAccountInCompany> findAll(){
        return privateAccountInCompanyRepository.findAll();
    }

    public PrivateAccountInCompany save(PrivateAccountInCompany p){
        return privateAccountInCompanyRepository.save(p);
    }
}
