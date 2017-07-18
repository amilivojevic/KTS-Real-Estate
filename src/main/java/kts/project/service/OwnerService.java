package kts.project.service;

import kts.project.model.Owner;
import kts.project.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nina on 17-Jul-17.
 */

@Service
public class OwnerService {

    @Autowired
    OwnerRepository ownerRepository;

    public Owner save(Owner o){
        return ownerRepository.save(o);
    }

    public Owner findByUsername(String username){
        return ownerRepository.findByUsername(username);
    }

    public void delete(Owner o){
        ownerRepository.delete(o);
    }

}
