package kts.project.repository;

import kts.project.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sandra on 6/12/2017.
 */
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
