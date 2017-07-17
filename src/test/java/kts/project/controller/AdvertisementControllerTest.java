package kts.project.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import kts.project.KtsprojectApplication;
import kts.project.LoginTest;
import kts.project.TestUtil;
import kts.project.controller.dto.AddAdvertisementDTO;
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

import static kts.project.constants.AdvertisementConstants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Sandra on 7/17/2017.
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KtsprojectApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class AdvertisementControllerTest {

    private static final String URL_PREFIX = "/api/advertisement";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    private LoginTest loginTest;

    /**
     * This method sets up MockMvc object
     */
    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * This method should registrate new admins and verifiers. Expecting all valid input
     * fields. Expected: method post, status CREATED, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testAddNewAdvertisement() throws Exception {
        AddAdvertisementDTO addAdvertisementDTO = new AddAdvertisementDTO();

        addAdvertisementDTO.setCurrency(NEWADV_CURRENCY);
        addAdvertisementDTO.setEndingDate(NEWADV_ENDING_DATE);
        addAdvertisementDTO.setId(NEWADV_RS_ID);
        addAdvertisementDTO.setPhoneNumber(NEWADV_PHONE_NUMBER);
        addAdvertisementDTO.setPrice(NEWADV_PRICE);
        addAdvertisementDTO.setTitle(NEWADV_TITLE);
        addAdvertisementDTO.setType(NEWADV_TYPE);

        String token = loginTest.login(USERNAME,PASSWORD);



        String json = TestUtil.json(addAdvertisementDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/addNewAdvertisement").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isCreated());
    }


}
