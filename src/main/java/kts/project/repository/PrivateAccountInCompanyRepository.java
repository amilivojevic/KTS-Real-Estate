package kts.project.repository;

import kts.project.model.PrivateAccountInCompany;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sandra on 6/12/2017.
 */
public interface PrivateAccountInCompanyRepository extends JpaRepository<PrivateAccountInCompany,Long>{

    PrivateAccountInCompany findById(Long id);

}
