package kts.project.controller;

/**
 * Created by Nina on 17-Jul-17.
 */

import kts.project.KtsprojectApplication;
import kts.project.LoginTest;
import kts.project.TestUtil;
import kts.project.controller.dto.RealEstateDTO;
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

import static kts.project.constants.RealEstateConstants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * This class tests RealEstate controller
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KtsprojectApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class RealEstateControllerTest {

    private static final String URL_PREFIX = "/api/realEstate";

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
     * This method should test adding new Real Estate with valid input.
     * Expect all fields to be full filed correctly. Expected: method post, status
     * CREATED, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testAddNewRealEstate() throws Exception {
        RealEstateDTO realEstateDTO = new RealEstateDTO();

        realEstateDTO.setDescription(NEW_DESCRIPTION);
        realEstateDTO.setImageUrl(NEW_IMAGE_URL);
        realEstateDTO.setFurniture(NEW_FURNITURE);
        realEstateDTO.setParking(NEW_PARKING);
        realEstateDTO.setArea(NEW_AREA);
        realEstateDTO.setConstructionYear(NEW_CONSTRUCTION_YEAR);
        realEstateDTO.setRoomsNumber(NEW_ROOMS_NUMBER);
        realEstateDTO.setBathroomsNumber(NEW_BATHROOMS_NUMBER);
        realEstateDTO.setCity(NEW_CITY);
        realEstateDTO.setCityArea(NEW_CITY_AREA);
        realEstateDTO.setStreet(NEW_STREET);
        realEstateDTO.setStreetNumber(NEW_STREET_NUMBER);
        realEstateDTO.setState(NEW_STATE);
        realEstateDTO.setZipCode(NEW_ZIP_CODE);
        realEstateDTO.setHeatingType(NEW_HEATING_TYPE);
        realEstateDTO.setRs_type(NEW_RS_TYPE);

        String token = loginTest.login(USERNAME,PASSWORD);
        String json = TestUtil.json(realEstateDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/reportBanedAdvertisement/").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isCreated());

    }


}
