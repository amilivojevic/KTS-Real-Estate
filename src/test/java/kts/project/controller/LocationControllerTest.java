package kts.project.controller;

import kts.project.KtsprojectApplication;
import kts.project.LoginTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

import static kts.project.constants.LocationConstants.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * This class tests Location controller
 *
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KtsprojectApplication.class)
@WebIntegrationTest
@TestPropertySource(locations = "classpath:test.properties")
public class LocationControllerTest {

    private static final String URL_PREFIX = "/api/location";

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
     * This method tests getting all Locations from database.
     * Expected: method get, status OK, and specified content
     *
     * @throws Exception
     **/
    @Test
    public void testGetAllLocations() throws Exception {

        mockMvc.perform(get(URL_PREFIX + "/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(DB_LOCATION_NUMBER)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_LOCATION_ID.intValue())))
                .andExpect(jsonPath("$.[*].city").value(hasItem(DB_LOCATION_CITY)))
                .andExpect(jsonPath("$.[*].cityArea").value(hasItem(DB_LOCATION_CITY_AREA)))
                .andExpect(jsonPath("$.[*].street").value(hasItem(DB_LOCATION_STREET)))
                .andExpect(jsonPath("$.[*].streetNumber").value(hasItem(DB_LOCATION_STREET_NUMBER)))
                .andExpect(jsonPath("$.[*].state").value(hasItem(DB_LOCATION_STATE)));
    }
}
