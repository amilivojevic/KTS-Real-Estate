package kts.project.repository;

import kts.project.model.PrivateAccountInCompany;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents PrivateAccountInCompany repository
 */
public interface PrivateAccountInCompanyRepository extends JpaRepository<PrivateAccountInCompany,Long>{

    /**
     * This method finds RealEstate with specified id
     * @param id
     * @return object of PrivateAccountInCompany
     */
    PrivateAccountInCompany findById(Long id);

}
