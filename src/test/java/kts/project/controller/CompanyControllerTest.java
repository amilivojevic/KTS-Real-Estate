package kts.project.controller;

import kts.project.KtsprojectApplication;
import kts.project.LoginTest;
import kts.project.TestUtil;
import kts.project.controller.dto.RegisterCompanyDTO;
import kts.project.model.enumerations.Role;
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

import static kts.project.constants.CompanyConstants.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * This class tests Company controller
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KtsprojectApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class CompanyControllerTest {

    private static final String URL_PREFIX = "/api/users/company";

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
     * This method should save new Company owner. Expecting all valid input
     * fields. Expected: method post, status CREATED, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveUser() throws Exception {

        RegisterCompanyDTO registerCompanyDTO = new RegisterCompanyDTO(
                NEW_COMPANY_TYPE ,
                NEW_COMPANY_ROLE ,
                NEW_COMPANY_USERNAME ,
                NEW_COMPANY_PASSWORD ,
                NEW_COMPANY_EMAIL ,
                NEW_COMPANY_NAME ,
                NEW_COMPANY_SURNAME ,
                NEW_COMPANY_BIRTH_DATE ,
                NEW_COMPANY_PHONE_NUMBER ,
                NEW_COMPANY_ADDRESS ,
                NEW_COMPANY_CITY ,
                NEW_COMPANY_COUNTRY ,
                NEW_COMPANY_ACCOUNT_NUMBER ,
                NEW_COMPANY_IMAGE_URL ,
                NEW_COMPANY_PIB ,
                NEW_COMPANY_SITE,
                NEW_COMPANY_FAX
            );
        String token = loginTest.login(USERNAME,PASSWORD);
        String json = TestUtil.json(registerCompanyDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isCreated());
    }

    /**
     * This method should save new Company owner. Expecting all invalid input
     * fields: Not unique username, Not unique pib, empty username, password, email or pib.
     * Expected: method post, status CREATED, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveUserInvalid() throws Exception {

        RegisterCompanyDTO registerCompanyDTO = new RegisterCompanyDTO(
                NEW_COMPANY_TYPE ,
                NEW_COMPANY_ROLE ,
                NEW_COMPANY_USERNAME ,
                NEW_COMPANY_PASSWORD,
                NEW_COMPANY_EMAIL ,
                NEW_COMPANY_NAME ,
                NEW_COMPANY_SURNAME ,
                NEW_COMPANY_BIRTH_DATE ,
                NEW_COMPANY_PHONE_NUMBER ,
                NEW_COMPANY_ADDRESS ,
                NEW_COMPANY_CITY ,
                NEW_COMPANY_COUNTRY ,
                NEW_COMPANY_ACCOUNT_NUMBER ,
                NEW_COMPANY_IMAGE_URL ,
                NEW_COMPANY_PIB ,
                NEW_COMPANY_SITE,
                NEW_COMPANY_FAX
        );

        registerCompanyDTO.setUsername(NEW_COMPANY_USERNAME_EXIST);
        String token = loginTest.login(USERNAME,PASSWORD);
        String json = TestUtil.json(registerCompanyDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        registerCompanyDTO.setUsername(NEW_COMPANY_USERNAME_EMPTY);
        json = TestUtil.json(registerCompanyDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        registerCompanyDTO = new RegisterCompanyDTO(
                NEW_COMPANY_TYPE ,
                NEW_COMPANY_ROLE ,
                NEW_COMPANY_USERNAME ,
                NEW_COMPANY_PASSWORD,
                NEW_COMPANY_EMAIL ,
                NEW_COMPANY_NAME ,
                NEW_COMPANY_SURNAME ,
                NEW_COMPANY_BIRTH_DATE ,
                NEW_COMPANY_PHONE_NUMBER ,
                NEW_COMPANY_ADDRESS ,
                NEW_COMPANY_CITY ,
                NEW_COMPANY_COUNTRY ,
                NEW_COMPANY_ACCOUNT_NUMBER ,
                NEW_COMPANY_IMAGE_URL ,
                NEW_COMPANY_PIB ,
                NEW_COMPANY_SITE,
                NEW_COMPANY_FAX
        );

        registerCompanyDTO.setPib(NEW_COMPANY_PIB_EXIST);
        json = TestUtil.json(registerCompanyDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        registerCompanyDTO.setUsername(NEW_COMPANY_PIB_EMPTY);
        json = TestUtil.json(registerCompanyDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        registerCompanyDTO = new RegisterCompanyDTO(
                NEW_COMPANY_TYPE ,
                NEW_COMPANY_ROLE ,
                NEW_COMPANY_USERNAME ,
                NEW_COMPANY_PASSWORD,
                NEW_COMPANY_EMAIL ,
                NEW_COMPANY_NAME ,
                NEW_COMPANY_SURNAME ,
                NEW_COMPANY_BIRTH_DATE ,
                NEW_COMPANY_PHONE_NUMBER ,
                NEW_COMPANY_ADDRESS ,
                NEW_COMPANY_CITY ,
                NEW_COMPANY_COUNTRY ,
                NEW_COMPANY_ACCOUNT_NUMBER ,
                NEW_COMPANY_IMAGE_URL ,
                NEW_COMPANY_PIB ,
                NEW_COMPANY_SITE,
                NEW_COMPANY_FAX
        );

        registerCompanyDTO.setEmail(NEW_COMPANY_EMAIL_EMPTY);
        json = TestUtil.json(registerCompanyDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        registerCompanyDTO = new RegisterCompanyDTO(
                NEW_COMPANY_TYPE ,
                NEW_COMPANY_ROLE ,
                NEW_COMPANY_USERNAME ,
                NEW_COMPANY_PASSWORD,
                NEW_COMPANY_EMAIL ,
                NEW_COMPANY_NAME ,
                NEW_COMPANY_SURNAME ,
                NEW_COMPANY_BIRTH_DATE ,
                NEW_COMPANY_PHONE_NUMBER ,
                NEW_COMPANY_ADDRESS ,
                NEW_COMPANY_CITY ,
                NEW_COMPANY_COUNTRY ,
                NEW_COMPANY_ACCOUNT_NUMBER ,
                NEW_COMPANY_IMAGE_URL ,
                NEW_COMPANY_PIB ,
                NEW_COMPANY_SITE,
                NEW_COMPANY_FAX
        );
        registerCompanyDTO.setPassword(NEW_COMPANY_PASSWORD_EMPTY);
        json = TestUtil.json(registerCompanyDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/register").header("X-Auth-Token", token).contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

    }

    /**
     * This method tests getting all Companies from database.
     * Expected: method get, status OK, and specified content
     *
     * @throws Exception
     **/
    @Test
    public void testGetAllCompanies() throws Exception {

        mockMvc.perform(get(URL_PREFIX + "/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(DB_COMPANY_NUMBER)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_COMPANY_ID.intValue())))
                .andExpect(jsonPath("$.[*].role").value(hasItem(DB_COMPANY_ROLE)))
                .andExpect(jsonPath("$.[*].username").value(hasItem(DB_COMPANY_USERNAME)))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DB_COMPANY_EMAIL)))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DB_COMPANY_NAME)))
                .andExpect(jsonPath("$.[*].surname").value(hasItem(DB_COMPANY_SURNAME)))
                .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DB_COMPANY_PHONE_NUMBER)))
                .andExpect(jsonPath("$.[*].address").value(hasItem(DB_COMPANY_ADDRESS)))
                .andExpect(jsonPath("$.[*].city").value(hasItem(DB_COMPANY_CITY)))
                .andExpect(jsonPath("$.[*].country").value(hasItem(DB_COMPANY_COUNTRY)))
                .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DB_COMPANY_ACCOUNT_NUMBER)))
                .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DB_COMPANY_IMAGE_URL)))
                .andExpect(jsonPath("$.[*].pib").value(hasItem(DB_COMPANY_PIB)))
                .andExpect(jsonPath("$.[*].site").value(hasItem(DB_COMPANY_SITE)))
                .andExpect(jsonPath("$.[*].fax").value(hasItem(DB_COMPANY_FAX)));
    }

    /**
     * This method tests getting all unapproved Companies from database.
     * Expected: method get, status OK, and specified content
     *
     * @throws Exception
     **/
    @Test
    public void testGetAllUnapprovedCompanies() throws Exception {

        mockMvc.perform(get(URL_PREFIX + "/all/unapproved"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(DB_UNAPPROVED_COMPANY_NUMBER)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_COMPANY_ID_UNAPPROVED.intValue())))
                .andExpect(jsonPath("$.[*].role").value(hasItem(DB_COMPANY_ROLE_UNAPPROVED)))
                .andExpect(jsonPath("$.[*].username").value(hasItem(DB_COMPANY_USERNAME_UNAPPROVED)))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DB_COMPANY_EMAIL_UNAPPROVED)))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DB_COMPANY_NAME_UNAPPROVED)))
                .andExpect(jsonPath("$.[*].surname").value(hasItem(DB_COMPANY_SURNAME_UNAPPROVED)))
                .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DB_COMPANY_PHONE_NUMBER_UNAPPROVED)))
                .andExpect(jsonPath("$.[*].address").value(hasItem(DB_COMPANY_ADDRESS_UNAPPROVED)))
                .andExpect(jsonPath("$.[*].city").value(hasItem(DB_COMPANY_CITY_UNAPPROVED)))
                .andExpect(jsonPath("$.[*].country").value(hasItem(DB_COMPANY_COUNTRY_UNAPPROVED)))
                .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DB_COMPANY_ACCOUNT_NUMBER_UNAPPROVED)))
                .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DB_COMPANY_IMAGE_URL_UNAPPROVED)))
                .andExpect(jsonPath("$.[*].pib").value(hasItem(DB_COMPANY_PIB_UNAPPROVED)))
                .andExpect(jsonPath("$.[*].site").value(hasItem(DB_COMPANY_SITE_UNAPPROVED)))
                .andExpect(jsonPath("$.[*].fax").value(hasItem(DB_COMPANY_FAX_UNAPPROVED)));
    }


    /**
     * This method tests getting an advertisements from the database with a
     * specified ID. Expected: method get, status OK, specified size and content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testApproved() throws Exception {
        String token = loginTest.login(USERNAME_ADMIN,PASSWORD_ADMIN);
        mockMvc.perform(get(URL_PREFIX + "/approve/" + DB_COMPANY_USERNAME_UNAPPROVED).header("X-Auth-Token", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(DB_COMPANY_ID_UNAPPROVED.intValue()))
                .andExpect(jsonPath("$.approved").value(true));
    }

}
