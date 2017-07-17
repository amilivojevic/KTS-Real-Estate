package kts.project.controller;

import kts.project.KtsprojectApplication;
import kts.project.LoginTest;
import kts.project.TestUtil;
import kts.project.constants.AdvertisementConstants;
import kts.project.controller.dto.AddAdvertisementDTO;
import kts.project.model.Advertisement;
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
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.security.krb5.Confounder.intValue;

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

    /**
     * This method tests adding new Advertisement and saving it to the database.
     * Expected invalid input fields, unique phone number. Expected: method
     * post, status BAD_REQUEST
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveAdvertisementInvalid() throws Exception {
        AddAdvertisementDTO addAdvertisementDTO = new AddAdvertisementDTO();
        String token = loginTest.login(USERNAME,PASSWORD);


        addAdvertisementDTO.setCurrency(NEWADV_CURRENCY_BAD);
        addAdvertisementDTO.setEndingDate(NEWADV_ENDING_DATE_BAD);
        addAdvertisementDTO.setId(NEWADV_RS_ID);
        addAdvertisementDTO.setPhoneNumber(NEWADV_PHONE_NUMBER_BAD);
        addAdvertisementDTO.setPrice(NEWADV_PRICE_BAD);
        addAdvertisementDTO.setTitle(NEWADV_TITLE_BAD);
        addAdvertisementDTO.setType(NEWADV_TYPE_BAD);
        String json = TestUtil.json(addAdvertisementDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/addNewAdvertisement").header("X-Auth-Token", token).contentType(contentType).content(json))
                    .andExpect(status().isBadRequest());

        addAdvertisementDTO.setCurrency(NEWADV_CURRENCY);
        addAdvertisementDTO.setEndingDate(NEWADV_ENDING_DATE);
        addAdvertisementDTO.setId(NEWADV_RS_ID_BAD);
        addAdvertisementDTO.setPhoneNumber(NEWADV_PHONE_NUMBER);
        addAdvertisementDTO.setPrice(NEWADV_PRICE);
        addAdvertisementDTO.setTitle(NEWADV_TITLE);
        addAdvertisementDTO.setType(NEWADV_TYPE);
        json = TestUtil.json(addAdvertisementDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/addNewAdvertisement").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isNotFound());
    }


    /**
     * This method tests adding new Advertisement and saving it to the database.
     * Expected invalid input fields, unique phone number. Expected: method
     * post, status BAD_REQUEST
     *
     * @throws Exception
     **/
    @Test
    public void testGetAllAdvertisements() throws Exception {

        mockMvc.perform(get(URL_PREFIX + "/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(DB_ADV_NUMBER)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ADV_ID.intValue())))
                .andExpect(jsonPath("$.[*].title").value(hasItem(DB_ADV_TITLE)))
                .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DB_ADV_PHONE_NUMBER)))
                .andExpect(jsonPath("$.[*].price").value(hasItem(DB_ADV_PRICE)))
                .andExpect(jsonPath("$.[*].realEstate.id").value(hasItem(DB_ADV_RS_ID.intValue())))
                .andExpect(jsonPath("$.[*].owner.id").value(hasItem(DB_ADV_OWNER_ID.intValue())))
                .andExpect(jsonPath("$.[*].currency").value(hasItem(DB_ADV_CURRENCY)))
                .andExpect(jsonPath("$.[*].type").value(hasItem(DB_ADV_TYPE)))
                .andExpect(jsonPath("$.[*].state").value(hasItem(DB_ADV_STATE)));
    }




}
