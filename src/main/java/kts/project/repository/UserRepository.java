package kts.project.repository;

import kts.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents User repository
 *
 */
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * This method finds User with specified username
     * @param username
     * @return object of User
     */
    User findByUsername(String username);

    /**
     * This method finds User with specified id
     * @param id
     * @return object of User
     */
    User findById(Long id);

    /**
     * This method finds User with specified email
     * @param email
     * @return object of User
     */
    User findByEmail(String email);

}