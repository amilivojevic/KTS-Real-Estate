package kts.project.repository;

import kts.project.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents VerifierReport repository
 */
public interface AdvertisementRepository extends JpaRepository<Advertisement,Long> {

    /**
     * This method finds Advertisement with specified id
     * @param id
     * @return object of Advertisement
     */
    Advertisement findById(Long id);


}
