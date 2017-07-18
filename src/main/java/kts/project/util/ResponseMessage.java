package kts.project.util;

import java.io.Serializable;

/**
 * This class represents custom message of HTTP response
 */
public class ResponseMessage implements Serializable {

    private String response;

    /**
     * Constructor
     * @param response
     */
    public ResponseMessage(String response) {
        this.response = response;
    }

    /**
     * Empty constructor
     */
    public ResponseMessage() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
