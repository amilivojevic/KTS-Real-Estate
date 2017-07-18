package kts.project.controller;

import kts.project.KtsprojectApplication;
import kts.project.LoginTest;
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

import static kts.project.constants.VeririerReportConstants.PASSWORD;
import static kts.project.constants.VeririerReportConstants.USERNAME;
import static kts.project.constants.VeririerReportConstants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * This class tests VerifierReport controller
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KtsprojectApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class VerifierReportControllerTest {

    private static final String URL_PREFIX = "/api/verifierReport";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    private LoginTest loginTest;

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
     * CREATED, and specified content
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

        String token = loginTest.login(USERNAME,PASSWORD);
        String json = TestUtil.json(verifierReportDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/reportBanedAdvertisement/" + NEW_ADVERTISEMENT).header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isCreated());

    }

    /**
     * This method should test reporting banned advertisement with report with invalid input.
     * Expect to miss some not nullable fields. Expected: method post, status
     * BAD_REQUEST, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testReportBannedAdvertisementInvalid() throws Exception {
        VerifierReportDTO verifierReportDTO = new VerifierReportDTO();

        String json = TestUtil.json(verifierReportDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/reportBanedAdvertisement/" + NEW_ADVERTISEMENT).contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        verifierReportDTO = new VerifierReportDTO();
        verifierReportDTO.setDescription(NEW_DESCRIPTION);
        verifierReportDTO.setDate(NEW_DATE);

        json = TestUtil.json(verifierReportDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/reportBanedAdvertisement/" + NEW_ADVERTISEMENT).contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        verifierReportDTO = new VerifierReportDTO();
        verifierReportDTO.setDescription(NEW_DESCRIPTION);
        verifierReportDTO.setBanningReason(NEW_BANNING_REASON);

        json = TestUtil.json(verifierReportDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/reportBanedAdvertisement/" + NEW_ADVERTISEMENT).contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        verifierReportDTO = new VerifierReportDTO();
        verifierReportDTO.setDate(NEW_DATE);
        verifierReportDTO.setBanningReason(NEW_BANNING_REASON);

        json = TestUtil.json(verifierReportDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/reportBanedAdvertisement/" + NEW_ADVERTISEMENT).contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }
}
