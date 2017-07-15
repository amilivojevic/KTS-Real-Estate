package kts.project.repository;

import kts.project.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by USER on 6/11/2017.
 */

public interface OwnerRepository extends JpaRepository<Owner,Long>{

    Owner findById(Long id);
    Owner findByUsername(String username);
}
