package kts.project.repository;

import kts.project.model.VerifierReport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nina on 16-Jul-17.
 */
public interface VerifierReportRepository  extends JpaRepository<VerifierReport,Long> {

    VerifierReport findById(Long id);

}
