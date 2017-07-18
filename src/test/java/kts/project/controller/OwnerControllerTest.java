package kts.project.controller;

import kts.project.KtsprojectApplication;
import kts.project.LoginTest;
import kts.project.TestUtil;
import kts.project.controller.dto.RegisterOwnerDTO;
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

import static kts.project.constants.OwnerConstants.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * This class tests Owner controller
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KtsprojectApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class OwnerControllerTest {
    private static final String URL_PREFIX = "/api/users/owner";

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
     * This method should test adding new Owner with valid input.
     * Expect all fields to be full filed correctly. Expected: method post, status
     * CREATED, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveOwner() throws Exception {
        RegisterOwnerDTO registerOwnerDTO = new RegisterOwnerDTO();

        registerOwnerDTO.setType(NEW_TYPE);
        registerOwnerDTO.setRole(NEW_ROLE);
        registerOwnerDTO.setUsername(NEW_USERNAME);
        registerOwnerDTO.setPassword(NEW_PASSWORD);
        registerOwnerDTO.setEmail(NEW_EMAIL);
        registerOwnerDTO.setName(NEW_NAME);
        registerOwnerDTO.setSurname(NEW_SURNAME);
        registerOwnerDTO.setBirthDate(NEW_BIRTH_DATE);
        registerOwnerDTO.setPhoneNumber(NEW_PHONE_NUMBER);
        registerOwnerDTO.setAddress(NEW_ADDRESS);
        registerOwnerDTO.setCity(NEW_CITY);
        registerOwnerDTO.setCountry(NEW_COUNTRY);
        registerOwnerDTO.setAccountNumber(NEW_ACCOUNT_NUMBER);
        registerOwnerDTO.setImageUrl(NEW_IMAGE_URL);

        String json = TestUtil.json(registerOwnerDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
                .andExpect(status().isCreated());
    }

    /**
     * This method should test adding new Owner with invalid input.
     * Expect to miss some not nullable fields. Expected: method post, status
     * BAD_REQUEST, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveOwnerInvalid() throws Exception {
        RegisterOwnerDTO registerOwnerDTO = null;

        String json = TestUtil.json(registerOwnerDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        registerOwnerDTO = new RegisterOwnerDTO();

        registerOwnerDTO.setType(BAD_TYPE);
        registerOwnerDTO.setRole(BAD_ROLE);
        registerOwnerDTO.setUsername(BAD_USERNAME);
        registerOwnerDTO.setPassword(BAD_PASSWORD);
        registerOwnerDTO.setEmail(BAD_EMAIL);
        registerOwnerDTO.setName(BAD_NAME);
        registerOwnerDTO.setSurname(BAD_SURNAME);
        registerOwnerDTO.setBirthDate(BAD_BIRTH_DATE);
        registerOwnerDTO.setPhoneNumber(BAD_PHONE_NUMBER);
        registerOwnerDTO.setAddress(BAD_ADDRESS);
        registerOwnerDTO.setCity(BAD_CITY);
        registerOwnerDTO.setCountry(BAD_COUNTRY);
        registerOwnerDTO.setAccountNumber(BAD_ACCOUNT_NUMBER);
        registerOwnerDTO.setImageUrl(BAD_IMAGE_URL);

        json = TestUtil.json(registerOwnerDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        registerOwnerDTO = new RegisterOwnerDTO();
        registerOwnerDTO.setType(NEW_TYPE);
        registerOwnerDTO.setRole(NEW_ROLE);
        registerOwnerDTO.setUsername(NEW_USERNAME);
        registerOwnerDTO.setPassword(NEW_PASSWORD);
        registerOwnerDTO.setEmail(NEW_EMAIL);
        registerOwnerDTO.setName(NEW_NAME);
        registerOwnerDTO.setSurname(NEW_SURNAME);
        registerOwnerDTO.setBirthDate(NEW_BIRTH_DATE);
        registerOwnerDTO.setPhoneNumber(NEW_PHONE_NUMBER);
        registerOwnerDTO.setAddress(NEW_ADDRESS);
        registerOwnerDTO.setCity(NEW_CITY);
        registerOwnerDTO.setCountry(NEW_COUNTRY);
        registerOwnerDTO.setAccountNumber(BAD_ACCOUNT_NUMBER);
        registerOwnerDTO.setImageUrl(NEW_IMAGE_URL);

        json = TestUtil.json(registerOwnerDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }
    /**
     * This method tests getting all Private accounts in Company with status unapproved
     *  Expected: method get, status OK
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testGetAllUsers() throws Exception {

        String token = loginTest.login(USERNAME,PASSWORD);

        mockMvc.perform(get(URL_PREFIX + "/getAll").header("X-Auth-Token", token))
                .andExpect(status().isOk())

                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(DB_O_NUMBER)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())));

    }

    /**
     * This method tests getting all Real Estate of one user
     * Expected invalid input fields, unique phone number. Expected: method
     * post, status BAD_REQUEST
     *
     * @throws Exception
     **/
    @Test
    public void testGetAllMyAdvertisements() throws Exception {

        String token = loginTest.login(USERNAME_O,PASSWORD_O);

        mockMvc.perform(get(URL_PREFIX + "/getAllMyAdvertisements").header("X-Auth-Token", token))
                .andExpect(status().isOk())

                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(DB_AD_NUMBER)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_A_ID.intValue())))
                .andExpect(jsonPath("$.[*].title").value(hasItem(DB_ADV_TITLE)))
                .andExpect(jsonPath("$.[*].price").value(hasItem(DB_ADV_PRICE)))
                .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DB_ADV_PHONE_NUMBER)))
                .andExpect(jsonPath("$.[*].type").value(hasItem(DB_ADV_TYPE)))
                .andExpect(jsonPath("$.[*].state").value(hasItem(DB_ADV_STATE)))
                .andExpect(jsonPath("$.[*].realEstate.id").value(hasItem(DB_ADV_RS_ID.intValue())))
                .andExpect(jsonPath("$.[*].currency").value(hasItem(DB_ADV_CURRENCY)))
                .andExpect(jsonPath("$.[*].owner.id").value(hasItem(DB_ADV_OWNER_ID.intValue())));

    }
}
