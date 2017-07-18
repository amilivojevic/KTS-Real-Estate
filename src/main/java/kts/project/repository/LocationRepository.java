package kts.project.repository;

import kts.project.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents Location repository
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
}
