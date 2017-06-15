package kts.project.util;

import java.io.Serializable;

/**
 * Created by Korisnik on 6/14/2017.
 */
public class ResponseMessage implements Serializable {
    private String response;

    public ResponseMessage(String response) {
        this.response = response;
    }

    public ResponseMessage() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
