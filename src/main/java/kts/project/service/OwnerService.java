package kts.project.service;

import kts.project.controller.dto.RegisterOwnerDTO;
import kts.project.model.Owner;
import kts.project.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class represents Owner Service
 *
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

    /**
     * This method is checking if all required inputs for RegisterOwnerDTO are entered
     * @param registerOwnerDTO
     * @return true or false
     */
    public boolean checkPrivateAccountInCompanyDTOInput(RegisterOwnerDTO registerOwnerDTO) {

        if (registerOwnerDTO == null) {
            return false;
        }
        if (registerOwnerDTO.getType().equals("") ||

                registerOwnerDTO.getRole().equals("") ||
                registerOwnerDTO.getUsername().equals("") ||
                registerOwnerDTO.getPassword().equals("") ||
                registerOwnerDTO.getEmail().equals("") ||
                registerOwnerDTO.getName().equals("") ||
                registerOwnerDTO.getSurname().equals("") ||
                registerOwnerDTO.getBirthDate() == null ||
                registerOwnerDTO.getPhoneNumber().equals("") ||
                registerOwnerDTO.getAddress().equals("") ||
                registerOwnerDTO.getCity().equals("") ||
                registerOwnerDTO.getCountry().equals("") ||
                registerOwnerDTO.getAccountNumber().equals("") ||
                registerOwnerDTO.getImageUrl().equals("")) {

            return false;
        } else {
            return true;
        }
    }

}
