package kts.project.constants;
import kts.project.model.Authority;
import kts.project.model.enumerations.Role;

import java.util.Date;

/**
 * Constants for user tests
 *
 */
public class UserContstants {

    public static final String NEW_USERNAME = "New username";
    public static final String NEW_PASSWORD = "pass";
    public static final String NEW_EMAIL = "adminovski@gmail.com";
    public static final String NEW_ROLE = "ADMIN";
    public static final String NEW_AUTHORITY = "1";
    public static final String NEW_NAME = "New name";
    public static final String NEW_SURNAME = "New surname";
    public static final Date NEW_BIRTH_DATE = new Date();
    public static final String NEW_PHONE_NUMBER = "+42342";
    public static final String NEW_ADDRESS = "Dusana Danilovica 7";
    public static final String NEW_CITY = "Novi Sad";
    public static final String NEW_COUNTRY = "Serbia";
    public static final String NEW_ACCOUNT_NUMBER = "0259841";
    public static final String NEW_IMAGE_URL = "image1.jpg";

    public static final String USERNAME = "a";
    public static final String PASSWORD = "a";

    public static final String USERNAME_A = "admin";
    public static final String PASSWORD_A = "admin";

    public static final String USERNAME_V = "ver";
    public static final String USERNAME_V_BAD = "mrs";
    public static final String PASSWORD_V = "ver";

    public static final Long DB_ID = 4L;

    public static final String DB_USERNAME = "a";
    public static final String DB_PASSWORD = "a";
    public static final String DB_EMAIL = "marko@gmail.com";
    public static final String DB_ROLE = Role.OWNER.name();
    public static final Long DB_AUTHORITY = 2L;
    public static final String DB_NAME = "marko";
    public static final String DB_SURNAME = "Markovic";
 //   public static final Date DB_BIRTH_DATE = new Date();
    public static final String DB_PHONE_NUMBER = "021 111 111";
    public static final String DB_ADDRESS = "Reja 54";
    public static final String DB_CITY = "Wroclaw";
    public static final String DB_COUNTRY = "Poland";
    public static final String DB_ACCOUNT_NUMBER = "3696";
    public static final String DB_IMAGE_URL = "images/img1.jpg";

    public static final int DB_USERS_NUMBER = 9;

    public static final String NEW_USERNAME_O = "Owner username";
    public static final String NEW_PASSWORD_O = "pass";
    public static final String NEW_EMAIL_O = "adminovski@gmail.com";
    public static final String NEW_ROLE_O = Role.OWNER.name();
    public static final String NEW_TYPE_O = "PrivateAcc";
    public static final String NEW_NAME_O = "New name";
    public static final String NEW_SURNAME_O = "New surname";
    public static final Date NEW_BIRTH_DATE_O = new Date();
    public static final String NEW_PHONE_NUMBER_O = "+42342";
    public static final String NEW_ADDRESS_O = "Dusana Danilovica 7";
    public static final String NEW_CITY_O = "Novi Sad";
    public static final String NEW_COUNTRY_O = "Serbia";
    public static final String NEW_ACCOUNT_NUMBER_O = "0259841";
    public static final String NEW_IMAGE_URL_O = "image1.jpg";
    public static final Long NEW_COMPANY_ID_O = 5L;

    /**
     * Empty constructor
     */
    public UserContstants() {
    }
}
