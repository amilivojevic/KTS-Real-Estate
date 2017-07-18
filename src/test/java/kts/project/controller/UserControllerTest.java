package kts.project.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import kts.project.KtsprojectApplication;
import kts.project.LoginTest;
import kts.project.TestUtil;
import kts.project.controller.dto.LoginDTO;
import kts.project.controller.dto.RegisterDTO;
import kts.project.controller.dto.RegisterOwnerDTO;
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

import static kts.project.constants.UserContstants.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Nina on 17-Jul-17.
 */

/**
 * This class tests User controller
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KtsprojectApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class UserControllerTest {

    private static final String URL_PREFIX = "/api/users";

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

    @Autowired
    LoginTest loginTest;

    /**
     * This method should test log in of users of all types. Expecting all valid input
     * fields. Expected: method post, status OK
     *
     * @throws Exception
     **/
    @Test
    public void testLogin() throws Exception {
        LoginDTO loginUser = new LoginDTO();
        loginUser.setUsername(DB_USERNAME);
        loginUser.setPassword(DB_PASSWORD);


        System.out.println("username" + loginUser.getUsername());
        System.out.println("password" + loginUser.getPassword());
        String json = TestUtil.json(loginUser);
        mockMvc.perform(post(URL_PREFIX + "/login").contentType(contentType).content(json)).andExpect(status().isOk());
    }

    /**
     * This method should test log in of users of all types. Expecting invalid input
     * fields. Expected: method post, status BAD_REQUEST
     *
     * @throws Exception
     */
    @Test
    public void testInvalidLogin() throws Exception {
        LoginDTO loginUser = new LoginDTO();
        loginUser.setUsername(NEW_USERNAME);
        loginUser.setPassword(NEW_PASSWORD);

        String json = TestUtil.json(loginUser);
        mockMvc.perform(post(URL_PREFIX + "/login").contentType(contentType).content(json))
                .andExpect(status().isNotFound());

        loginUser = new LoginDTO();
        loginUser.setUsername(NEW_USERNAME);
        loginUser.setPassword(DB_PASSWORD);

        json = TestUtil.json(loginUser);
        mockMvc.perform(post(URL_PREFIX + "/login").contentType(contentType).content(json))
                .andExpect(status().isNotFound());

        loginUser = new LoginDTO();
        loginUser.setUsername(DB_USERNAME);
        loginUser.setPassword(NEW_PASSWORD);

        json = TestUtil.json(loginUser);
        mockMvc.perform(post(URL_PREFIX + "/login").contentType(contentType).content(json))
                .andExpect(status().isNotFound());

        String json1 = TestUtil.json(new LoginDTO());
        mockMvc.perform(post(URL_PREFIX + "/login").contentType(contentType).content(json1))
                .andExpect(status().isBadRequest());

    }

    /**
     * This method should test registration new admins and verifiers with valid input.
     * Expect all fields to be full filed correctly. Expected: method post, status
     * CREATED, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveUser() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO();

        registerDTO.setRole(NEW_ROLE);
        registerDTO.setUsername(NEW_USERNAME);
        registerDTO.setPassword(NEW_PASSWORD);
        registerDTO.setEmail(NEW_EMAIL);
        registerDTO.setName(NEW_NAME);
        registerDTO.setSurname(NEW_SURNAME);
        registerDTO.setBirthDate(NEW_BIRTH_DATE);
        registerDTO.setAddress(NEW_ADDRESS);
        registerDTO.setAccountNumber(NEW_ACCOUNT_NUMBER);
        registerDTO.setCity(NEW_CITY);
        registerDTO.setCountry(NEW_COUNTRY);
        registerDTO.setImageUrl(NEW_IMAGE_URL);
        registerDTO.setPhoneNumber(NEW_PHONE_NUMBER);


        String json = TestUtil.json(registerDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/admin/register").contentType(contentType).content(json))
                .andExpect(status().isCreated());
    }


    /**
     * This method should test registration new admins and verifiers with invalid input.
     * Expect to miss some not nullable fields. Expected: method post, status
     * BAD_REQUEST, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveUserInvalid() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO();

        String json = TestUtil.json(registerDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/admin/register").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        registerDTO = new RegisterDTO();
        registerDTO.setEmail(NEW_EMAIL);
        registerDTO.setPassword(NEW_PASSWORD);

        json = TestUtil.json(registerDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/admin/register").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        registerDTO = new RegisterDTO();
        registerDTO.setEmail(NEW_EMAIL);
        registerDTO.setUsername(NEW_USERNAME);

        json = TestUtil.json(registerDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/admin/register").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        registerDTO = new RegisterDTO();
        registerDTO.setUsername(NEW_USERNAME);
        registerDTO.setPassword(NEW_PASSWORD);

        json = TestUtil.json(registerDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/admin/register").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());
    }

    /**
     * This method should test registration new admins and verifiers with data that already
     * exist. Expected: method post, status BAD_REQUEST, and specified content
     *
     * @throws Exception
     **/
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveUserUnique() throws Exception {
        RegisterDTO registerDTO = new RegisterDTO();

        String json = TestUtil.json(registerDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/admin/register").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

        registerDTO.setEmail(NEW_EMAIL);
        registerDTO.setPassword(NEW_PASSWORD);
        registerDTO.setUsername(kts.project.constants.UserContstants.DB_USERNAME);

        json = TestUtil.json(registerDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/admin/register").contentType(contentType).content(json))
                .andExpect(status().isBadRequest());

    }

    /**
     * This method should test registration of new Owner with valid input.
     * Expect all fields to be full filed correctly. Expected: method post, status
     * CREATED, and specified content
     *
     * @throws Exception
     **/
 /*   @Test
    @Transactional
    @Rollback(true)
    public void saveOwner() throws Exception {
        RegisterPrivateAccDTO registerPrivateAccDTO = new RegisterPrivateAccDTO();

        registerPrivateAccDTO.setRole(NEW_ROLE_O);
        registerPrivateAccDTO.setUsername(NEW_USERNAME_O);
        registerPrivateAccDTO.setPassword(NEW_PASSWORD_O);
        registerPrivateAccDTO.setEmail(NEW_EMAIL_O);
        registerPrivateAccDTO.setName(NEW_NAME_O);
        registerPrivateAccDTO.setSurname(NEW_SURNAME_O);
        registerPrivateAccDTO.setBirthDate(NEW_BIRTH_DATE_O);
        registerPrivateAccDTO.setAddress(NEW_ADDRESS_O);
        registerPrivateAccDTO.setAccountNumber(NEW_ACCOUNT_NUMBER_O);
        registerPrivateAccDTO.setCity(NEW_CITY_O);
        registerPrivateAccDTO.setCountry(NEW_COUNTRY_O);
        registerPrivateAccDTO.setImageUrl(NEW_IMAGE_URL_O);
        registerPrivateAccDTO.setPhoneNumber(NEW_PHONE_NUMBER_O);


        String json = TestUtil.json(registerPrivateAccDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/privateAcc/register").contentType(contentType).content(json))
                .andExpect(status().isCreated());
    }*/

    /**
     * This method is getting all data of one User
     * Expected: method get, status
     * OK, and specified content
     *
     * @throws Exception
     */
    @Test
    public void testGetData() throws Exception {

        String token = loginTest.login(USERNAME,PASSWORD);
        mockMvc.perform(get(URL_PREFIX + "/data").header("X-Auth-Token", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(DB_ID.intValue()))
                .andExpect(jsonPath("$.username").value(DB_USERNAME))
                .andExpect(jsonPath("$.email").value(DB_EMAIL))
                .andExpect(jsonPath("$.role").value(DB_ROLE))
                .andExpect(jsonPath("$.authority.id").value(DB_AUTHORITY.intValue()))
                .andExpect(jsonPath("$.name").value(DB_NAME))
                .andExpect(jsonPath("$.surname").value(DB_SURNAME))
                .andExpect(jsonPath("$.phoneNumber").value(DB_PHONE_NUMBER))
                .andExpect(jsonPath("$.address").value(DB_ADDRESS))
                .andExpect(jsonPath("$.city").value(DB_CITY))
                .andExpect(jsonPath("$.country").value(DB_COUNTRY))
                .andExpect(jsonPath("$.accountNumber").value(DB_ACCOUNT_NUMBER))
                .andExpect(jsonPath("$.imageUrl").value(DB_IMAGE_URL));
    }

    /**
     * This method is getting all Users from database
     * Expected: method get, status
     * OK, and specified content
     *
     * @throws Exception
     */
    @Test
    public void testGetAllUsers() throws Exception {

        String token = loginTest.login(USERNAME,PASSWORD);
        mockMvc.perform(get(URL_PREFIX + "/getAll").header("X-Auth-Token", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(DB_USERS_NUMBER)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
                .andExpect(jsonPath("$.[*].username").value(hasItem(DB_USERNAME)))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DB_EMAIL)))
                .andExpect(jsonPath("$.[*].role").value(hasItem(DB_ROLE)))
                .andExpect(jsonPath("$.[*].authority.id").value(hasItem(DB_AUTHORITY.intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
                .andExpect(jsonPath("$.[*].surname").value(hasItem(DB_SURNAME)))
                .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DB_PHONE_NUMBER)))
                .andExpect(jsonPath("$.[*].address").value(hasItem(DB_ADDRESS)))
                .andExpect(jsonPath("$.[*].city").value(hasItem(DB_CITY)))
                .andExpect(jsonPath("$.[*].country").value(hasItem(DB_COUNTRY)))
                .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DB_ACCOUNT_NUMBER)))
                .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DB_IMAGE_URL)));
    }

    /**
     * This method is getting all Users from database
     * Expected: method get, status
     * OK, and specified content
     *
     * @throws Exception
     */
 /*   @Test
    public void testGetAllUsers() throws Exception {

        String token = loginTest.login(USERNAME,PASSWORD);
        mockMvc.perform(get(URL_PREFIX + "/verifier/getAll").header("X-Auth-Token", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(DB_USERS_NUMBER)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
                .andExpect(jsonPath("$.[*].username").value(hasItem(DB_USERNAME)))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DB_EMAIL)))
                .andExpect(jsonPath("$.[*].role").value(hasItem(DB_ROLE)))
                .andExpect(jsonPath("$.[*].authority.id").value(hasItem(DB_AUTHORITY.intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
                .andExpect(jsonPath("$.[*].surname").value(hasItem(DB_SURNAME)))
                .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DB_PHONE_NUMBER)))
                .andExpect(jsonPath("$.[*].address").value(hasItem(DB_ADDRESS)))
                .andExpect(jsonPath("$.[*].city").value(hasItem(DB_CITY)))
                .andExpect(jsonPath("$.[*].country").value(hasItem(DB_COUNTRY)))
                .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DB_ACCOUNT_NUMBER)))
                .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DB_IMAGE_URL)));
    } */

    /**
     * This method tests deleting of existing Verifier. Expected: method
     * get, status OK
     * @throws Exception
     */
    @Test
    @javax.transaction.Transactional
    @Rollback(true)
    public void testErase() throws Exception {
        String token = loginTest.login(USERNAME_A, PASSWORD_A);
        mockMvc.perform(get(URL_PREFIX + "/verifier/erase/" + USERNAME_V).header("X-Auth-Token", token))
                .andExpect(status().isOk());
    }

    /**
     * This method tests deleting of non existing User. Expected: method
     * get, status NOT_FOUND
     * @throws Exception
     */
    @Test
    @javax.transaction.Transactional
    @Rollback(true)
    public void testEraseWrong() throws Exception {
        String token = loginTest.login(USERNAME_A, PASSWORD_A);
        mockMvc.perform(get(URL_PREFIX + "/verifier/erase/" + USERNAME_V_BAD).header("X-Auth-Token", token))
                .andExpect(status().isNotFound());
    }


}
