package kts.project.controller;

import kts.project.KtsprojectApplication;
import kts.project.TestUtil;
import kts.project.controller.dto.RegisterDTO;
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

    /**
     * This method should registrate new admins and verifiers. Expecting all valid input
     * fields. Expected: method post, status CREATED, and specified content
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
     * This method should test registrating new admins and verifiers with invalid input.
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






}
