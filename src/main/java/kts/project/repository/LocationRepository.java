package kts.project.repository;

import kts.project.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by USER on 6/11/2017.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
