package kts.project.repository;

import kts.project.model.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * This interface represents RealEstate repository
 */
@Repository
public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {

    /**
     * This method finds RealEstate with specified id
     * @param id
     * @return object of RealEstate
     */
    RealEstate findById(Long id);
}

