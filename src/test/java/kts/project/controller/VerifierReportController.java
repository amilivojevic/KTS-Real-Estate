package kts.project.controller;

import kts.project.KtsprojectApplication;
import kts.project.TestUtil;
import kts.project.controller.dto.VerifierReportDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

import static kts.project.constants.VeririerReportConstants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Nina on 17-Jul-17.
 */

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KtsprojectApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class VerifierReportController {

    private static final String URL_PREFIX = "api/verifierReport";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * This method sets up MockMvc object
     */
    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * This method should test reporting banned advertisement with report with valid input.
     * Expect all fields to be full filed correctly. Expected: method post, status
     * OK, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testReportBannedAdvertisement() throws Exception {
        VerifierReportDTO verifierReportDTO = new VerifierReportDTO();

        verifierReportDTO.setDescription(NEW_DESCRIPTION);
        verifierReportDTO.setDate(NEW_DATE);
        verifierReportDTO.setBanningReason(NEW_BANNING_REASON);
        verifierReportDTO.setAdvertisementId(NEW_ADVERTISEMENT);

        String json = TestUtil.json(verifierReportDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/reportBanedAdvertisement/" + NEW_ADVERTISEMENT).contentType(contentType).content(json))
                .andExpect(status().isCreated());
    }


}
