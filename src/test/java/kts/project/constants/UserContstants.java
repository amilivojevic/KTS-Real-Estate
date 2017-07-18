package kts.project.constants;

/**
 * Created by Nina on 17-Jul-17.
 */

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

    public static final Long DB_ID = 2L;

    public static final String DB_USERNAME = "admin";
    public static final String DB_PASSWORD = "admin";
    public static final String DB_EMAIL = "nina@gmail.com";
    public static final String DB_ROLE = "ADMIN";
    public static final String DB_AUTHORITY = "1";
    public static final String DB_NAME = "pera";
    public static final String DB_SURNAME = "peric";
    public static final Date DB_BIRTH_DATE = new Date();
    public static final String DB_PHONE_NUMBER = "06415896325";
    public static final String DB_ADDRESS = "Trg Majke Jevrosime 11";
    public static final String DB_CITY = "Wroclaw";
    public static final String DB_COUNTRY = "Poland";
    public static final String DB_ACCOUNT_NUMBER = "0012300";
    public static final String DB_IMAGE_URL = "image2.jpg";

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

    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Date birthDate;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String accountNumber;
    private String imageUrl;
    private long companyId;

}
