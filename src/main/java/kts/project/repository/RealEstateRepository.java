package kts.project.repository;

import kts.project.model.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nina on 14-Jul-17.
 */
@Repository
public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {

    RealEstate findById(Long id);
}

