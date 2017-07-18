package kts.project.repository;

import kts.project.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents Company repository
 */
public interface CompanyRepository extends JpaRepository<Company,Long> {

    /**
     * This method finds Company with specified username
     * @param username
     * @return object of Company
     */
    Company findByUsername(String username);

    /**
     * This method finds Company with specified id
     * @param id
     * @return object of Company
     */
    Company findById(Long id);
}
