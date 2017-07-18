package kts.project.repository;

import kts.project.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents Owner repository
 */
public interface OwnerRepository extends JpaRepository<Owner,Long>{

    /**
     * This method finds Owner with specified id
     * @param id
     * @return object of Owner
     */
    Owner findById(Long id);

    /**
     * This method finds Owner with specified username
     * @param username
     * @return object of Owner
     */
    Owner findByUsername(String username);
}
