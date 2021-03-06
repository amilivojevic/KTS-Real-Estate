package kts.project.repository;

import kts.project.model.VerifierReport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents VerifierReport repository
 */
public interface VerifierReportRepository  extends JpaRepository<VerifierReport,Long> {

    /**
     * This method finds VerifierReport with specified id
     * @param id
     * @return object of VerifierReport
     */
    VerifierReport findById(Long id);

}
