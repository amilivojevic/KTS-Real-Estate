package kts.project.repository;

import kts.project.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by USER on 6/11/2017.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
        Authority findByName(String name);
}
