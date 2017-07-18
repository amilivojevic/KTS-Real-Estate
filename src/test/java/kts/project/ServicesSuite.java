package kts.project;

import kts.project.controller.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class represents ServicesSuite
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		AdvertisementControllerTest.class,
		CompanyControllerTest.class,
		LocationControllerTest.class,
		OwnerControllerTest.class,
		PrivateAccountInCompanyControllerTest.class,
		RealEstateControllerTest.class,
		UserControllerTest.class,
		VerifierReportControllerTest.class
})
public class ServicesSuite {

}
