package kts.project.service;

import kts.project.controller.dto.RegisterPrivateAccDTO;
import kts.project.model.PrivateAccountInCompany;
import kts.project.repository.PrivateAccountInCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * This class represents Private Account in Company Service
 *
 */
@Service
public class PrivateAccountInCompanyService {

    @Autowired
    PrivateAccountInCompanyRepository privateAccountInCompanyRepository;

    /**
     * This method is finding one Real Estate by its Id
     * @param id
     * @return RealEstate with specified id
     */
    public PrivateAccountInCompany findById(Long id){
        return privateAccountInCompanyRepository.findById(id);
    }

    /**
     * This method is finding all Private Accounts in Company
     * @return list of PrivateAccountInCompany
     */
    public List<PrivateAccountInCompany> findAll(){
        return privateAccountInCompanyRepository.findAll();
    }

    /**
     * This method saves element to the database.
     *
     * @param p
     *            element to be saved
     * @return Saved element
     */
    public PrivateAccountInCompany save(PrivateAccountInCompany p){
        return privateAccountInCompanyRepository.save(p);
    }


    /**
     * This method is checking if all required inputs for RealEstateDTO are entered
     * @param registerPrivateAccDTO
     * @return true or false
     */
    public boolean checkPrivateAccountInCompanyDTOInput(RegisterPrivateAccDTO registerPrivateAccDTO) {

        if (registerPrivateAccDTO == null) {
            return false;
        }
        if (registerPrivateAccDTO.getType().equals("") ||

                registerPrivateAccDTO.getRole().equals("") ||
                registerPrivateAccDTO.getUsername().equals("") ||
                registerPrivateAccDTO.getPassword().equals("") ||
                registerPrivateAccDTO.getEmail().equals("") ||
                registerPrivateAccDTO.getName().equals("") ||
                registerPrivateAccDTO.getSurname().equals("") ||
                registerPrivateAccDTO.getBirthDate() == null ||
                registerPrivateAccDTO.getPhoneNumber().equals("") ||
                registerPrivateAccDTO.getAddress().equals("") ||
                registerPrivateAccDTO.getCity().equals("") ||
                registerPrivateAccDTO.getCountry().equals("") ||
                registerPrivateAccDTO.getAccountNumber().equals("") ||
                registerPrivateAccDTO.getImageUrl().equals("") ||
                registerPrivateAccDTO.getCompanyId() < 0) {

            return false;
        } else {
            return true;
        }
    }
}
