package kts.project.repository;

import kts.project.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents Authority repository
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

        /**
         * This method finds Authority with specified name
         * @param name
         * @return object of Authority
         */
        Authority findByName(String name);
}
