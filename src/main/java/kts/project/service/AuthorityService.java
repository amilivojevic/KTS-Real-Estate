package kts.project.service;

import kts.project.model.Authority;
import kts.project.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nina on 17-Jul-17.
 */

@Service
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    public Authority findByName(String name){
        return authorityRepository.findByName(name);
    }

}
