package kts.project.controller;

import kts.project.KtsprojectApplication;
import kts.project.LoginTest;
import kts.project.TestUtil;
import kts.project.controller.dto.RegisterPrivateAccDTO;
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
import java.util.Date;

import static kts.project.constants.PrivateAccountInCompanyConstants.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * This class tests PrivateAccountInCompany controller
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KtsprojectApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class PrivateAccountInCompanyControllerTest{

    private static final String URL_PREFIX = "/api/users/private_acc";

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
     * This method should test adding new Private account inside Company with valid input.
     * Expect all fields to be full filed correctly. Expected: method post, status
     * CREATED, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testSavePrivateAccount() throws Exception {
        RegisterPrivateAccDTO registerPrivateAccDTO = new RegisterPrivateAccDTO();

        registerPrivateAccDTO.setType(NEW_TYPE);
        registerPrivateAccDTO.setRole(NEW_ROLE);
        registerPrivateAccDTO.setUsername(NEW_USERNAME);
        registerPrivateAccDTO.setPassword(NEW_PASSWORD);
        registerPrivateAccDTO.setEmail(NEW_EMAIL);
        registerPrivateAccDTO.setName(NEW_NAME);
        registerPrivateAccDTO.setSurname(NEW_SURNAME);
        registerPrivateAccDTO.setBirthDate(NEW_BIRTH_DATE);
        registerPrivateAccDTO.setPhoneNumber(NEW_PHONE_NUMBER);
        registerPrivateAccDTO.setAddress(NEW_ADDRESS);
        registerPrivateAccDTO.setCity(NEW_CITY);
        registerPrivateAccDTO.setCountry(NEW_COUNTRY);
        registerPrivateAccDTO.setAccountNumber(NEW_ACCOUNT_NUMBER);
        registerPrivateAccDTO.setImageUrl(NEW_IMAGE_URL);
        registerPrivateAccDTO.setCompanyId(NEW_COMPANY_ID);

        String json = TestUtil.json(registerPrivateAccDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
                .andExpect(status().isCreated());
    }

    /**
     * This method should test adding new Private account inside Company with invalid input.
     * Expect to miss some not nullable fields. Expected: method post, status
     * BAD_REQUEST, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testSavePrivateAccountInvalid() throws Exception {
        RegisterPrivateAccDTO registerPrivateAccDTO = null;

        String json = TestUtil.json(registerPrivateAccDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        registerPrivateAccDTO = new RegisterPrivateAccDTO();

        registerPrivateAccDTO.setType(BAD_TYPE);
        registerPrivateAccDTO.setRole(BAD_ROLE);
        registerPrivateAccDTO.setUsername(BAD_USERNAME);
        registerPrivateAccDTO.setPassword(BAD_PASSWORD);
        registerPrivateAccDTO.setEmail(BAD_EMAIL);
        registerPrivateAccDTO.setName(BAD_NAME);
        registerPrivateAccDTO.setSurname(BAD_SURNAME);
        registerPrivateAccDTO.setBirthDate(BAD_BIRTH_DATE);
        registerPrivateAccDTO.setPhoneNumber(BAD_PHONE_NUMBER);
        registerPrivateAccDTO.setAddress(BAD_ADDRESS);
        registerPrivateAccDTO.setCity(BAD_CITY);
        registerPrivateAccDTO.setCountry(BAD_COUNTRY);
        registerPrivateAccDTO.setAccountNumber(BAD_ACCOUNT_NUMBER);
        registerPrivateAccDTO.setImageUrl(BAD_IMAGE_URL);
        registerPrivateAccDTO.setCompanyId(BAD_COMPANY_ID);

        json = TestUtil.json(registerPrivateAccDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        registerPrivateAccDTO = new RegisterPrivateAccDTO();
        registerPrivateAccDTO.setType(NEW_TYPE);
        registerPrivateAccDTO.setRole(NEW_ROLE);
        registerPrivateAccDTO.setUsername(NEW_USERNAME);
        registerPrivateAccDTO.setPassword(NEW_PASSWORD);
        registerPrivateAccDTO.setEmail(NEW_EMAIL);
        registerPrivateAccDTO.setName(NEW_NAME);
        registerPrivateAccDTO.setSurname(NEW_SURNAME);
        registerPrivateAccDTO.setBirthDate(NEW_BIRTH_DATE);
        registerPrivateAccDTO.setPhoneNumber(NEW_PHONE_NUMBER);
        registerPrivateAccDTO.setAddress(NEW_ADDRESS);
        registerPrivateAccDTO.setCity(NEW_CITY);
        registerPrivateAccDTO.setCountry(NEW_COUNTRY);
        registerPrivateAccDTO.setAccountNumber(BAD_ACCOUNT_NUMBER);
        registerPrivateAccDTO.setImageUrl(NEW_IMAGE_URL);
        registerPrivateAccDTO.setCompanyId(NEW_COMPANY_ID);

        json = TestUtil.json(registerPrivateAccDTO);
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
    public void testGetAllUnapproved() throws Exception {

        String token = loginTest.login(USERNAME,PASSWORD);

        mockMvc.perform(get(URL_PREFIX + "/all/unapproved").header("X-Auth-Token", token))
                .andExpect(status().isOk())

                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(DB_PAC_NUMBER_UA)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
                .andExpect(jsonPath("$.[*].approved").value(hasItem(DB_APPROVED)))
                .andExpect(jsonPath("$.[*].company.id").value(hasItem(DB_COMPANY.intValue())))
                .andExpect(jsonPath("$.[*].owner.id").value(hasItem(DB_OWNER.intValue())));

    }

      /**
       * This method tests gets one unapproved Private accounts in Company in adition to change its status to approved
       * Expected: method get, status OK
       *
       * @throws Exception
       * **/
    @Test
    @Transactional
    @Rollback(true)
    public void testApprove() throws Exception {

        String token = loginTest.login(USERNAME,PASSWORD);

        mockMvc.perform(get(URL_PREFIX + "/approve/" + DB_ID_UNAPPROVED).header("X-Auth-Token", token))
                .andExpect(status().isOk())

                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(DB_ID_UNAPPROVED.intValue()))
                .andExpect(jsonPath("$.approved").value(DB_APPROVED1))
                .andExpect(jsonPath("$.company_id.id").value(DB_COMPANY1.intValue()))
                .andExpect(jsonPath("$.owner_id.id").value(DB_OWNER1.intValue()));
    }
}
