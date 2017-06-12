package kts.project.repository;

import kts.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by USER on 6/11/2017.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
