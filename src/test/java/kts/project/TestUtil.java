package kts.project;

/**
 * Created by Nina on 17-Jul-17.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * This class represents test utils
 */
public class TestUtil {

    /**
     * Constructor
     */
    private TestUtil() {}

    /**
     * This method convert specified object to json
     * @param object
     * @return JSON
     * @throws IOException
     */
    public static String json(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper.writeValueAsString(object);
    }
}
