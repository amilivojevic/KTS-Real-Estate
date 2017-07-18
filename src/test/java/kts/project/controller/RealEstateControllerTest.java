package kts.project.controller;

import kts.project.KtsprojectApplication;
import kts.project.LoginTest;
import kts.project.TestUtil;
import kts.project.controller.dto.RealEstateDTO;
import kts.project.model.RealEstate;
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
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        this.mockMvc.perform(post(URL_PREFIX + "/addNewRealEstate").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isCreated());

    }

    /**
     * This method should test adding new Real Estate with report with invalid input.
     * Expect to miss some not nullable fields. Expected: method post, status
     * BAD_REQUEST, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testAddNewRealEstateInvalid() throws Exception {
        RealEstateDTO realEstateDTO = null;

        String token = loginTest.login(USERNAME,PASSWORD);
        String json = TestUtil.json(realEstateDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/addNewRealEstate").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        realEstateDTO = new RealEstateDTO();
        realEstateDTO.setDescription(BAD_DESCRIPTION);
        realEstateDTO.setImageUrl(BAD_IMAGE_URL);
        realEstateDTO.setFurniture(BAD_FURNITURE);
        realEstateDTO.setParking(BAD_PARKING);
        realEstateDTO.setArea(BAD_AREA);
        realEstateDTO.setConstructionYear(BAD_CONSTRUCTION_YEAR);
        realEstateDTO.setRoomsNumber(BAD_ROOMS_NUMBER);
        realEstateDTO.setBathroomsNumber(BAD_BATHROOMS_NUMBER);
        realEstateDTO.setCity(BAD_CITY);
        realEstateDTO.setCityArea(BAD_CITY_AREA);
        realEstateDTO.setStreet(BAD_STREET);
        realEstateDTO.setStreetNumber(BAD_STREET_NUMBER);
        realEstateDTO.setState(BAD_STATE);
        realEstateDTO.setZipCode(BAD_ZIP_CODE);
        realEstateDTO.setHeatingType(BAD_HEATING_TYPE);
        realEstateDTO.setRs_type(BAD_RS_TYPE);

        token = loginTest.login(USERNAME,PASSWORD);
        json = TestUtil.json(realEstateDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/addNewRealEstate").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        realEstateDTO = new RealEstateDTO();
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
        realEstateDTO.setHeatingType(BAD_HEATING_TYPE);
        realEstateDTO.setRs_type(BAD_RS_TYPE);

        token = loginTest.login(USERNAME,PASSWORD);
        json = TestUtil.json(realEstateDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/addNewRealEstate").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

    }

    /**
     * This method tests getting all Real Estate of one user
     * Expected invalid input fields, unique phone number. Expected: method
     * post, status BAD_REQUEST
     *
     * @throws Exception
     **/
    @Test
    public void testGetAllMyRealEstates() throws Exception {

        String token = loginTest.login(USERNAME,PASSWORD);

        mockMvc.perform(get(URL_PREFIX + "/getAllMyRealEstates").header("X-Auth-Token", token))
                .andExpect(status().isOk())

                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(DB_ADV_NUMBER)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DB_DESCRIPTION)))
                .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DB_IMAGE_URL)))
                .andExpect(jsonPath("$.[*].furniture").value(hasItem(DB_FURNITURE)))
                .andExpect(jsonPath("$.[*].parking").value(hasItem(DB_PARKING)))
                .andExpect(jsonPath("$.[*].area").value(hasItem(DB_AREA)))
                .andExpect(jsonPath("$.[*].constructionYear").value(hasItem(DB_CONSTRUCTION_YEAR)))
                .andExpect(jsonPath("$.[*].roomsNumber").value(hasItem(DB_ROOMS_NUMBER)))
                .andExpect(jsonPath("$.[*].bathroomsNumber").value(hasItem(DB_BATHROOMS_NUMBER)))
                .andExpect(jsonPath("$.[*].address.id").value(hasItem(DB_LOCATION_ID.intValue())))
                .andExpect(jsonPath("$.[*].owner.id").value(hasItem(DB_OWNER_ID.intValue())))
                .andExpect(jsonPath("$.[*].heatingType").value(hasItem(DB_HEATING_TYPE)))
                .andExpect(jsonPath("$.[*].rs_type").value(hasItem(DB_RS_TYPE)));

    }

    /**
     * This method tests deleting of existing Real Estate. Expected: method
     * get, status OK
     * @throws Exception
     */
    @Test
    @javax.transaction.Transactional
    @Rollback(true)
    public void testErase() throws Exception {
        String token = loginTest.login(USERNAME, PASSWORD);
        mockMvc.perform(get(URL_PREFIX + "/erase/" + DB_ID).header("X-Auth-Token", token))
                .andExpect(status().isOk());
    }

    /**
     * This method tests deleting of non existing Real Estate. Expected: method
     * get, status NOT_FOUND
     * @throws Exception
     */
    @Test
    @javax.transaction.Transactional
    @Rollback(true)
    public void testEraseWrong() throws Exception {
        String token = loginTest.login(USERNAME, PASSWORD);
        mockMvc.perform(get(URL_PREFIX + "/erase/" + BAD_ID).header("X-Auth-Token", token))
                .andExpect(status().isNotFound());
    }
}
