package kts.project.repository;

import kts.project.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nina on 14-Jul-17.
 */
public interface AdvertisementRepository extends JpaRepository<Advertisement,Long> {

    Advertisement findById(Long id);


}
