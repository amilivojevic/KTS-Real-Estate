package kts.project.constants;

import kts.project.model.Authority;
import kts.project.model.enumerations.AdvertisementState;
import kts.project.model.enumerations.AdvertisementType;
import kts.project.model.enumerations.Currency;
import kts.project.model.enumerations.Role;

import java.util.Date;

/**
 * Constants for Owner tests
 *
 */
public class OwnerConstants
{

    public static final String USERNAME = "admin";
    public static final String PASSWORD = "admin";

    public static final String USERNAME_O = "a";
    public static final String PASSWORD_O = "a";

    public static final String NEW_TYPE = "Owner";
    public static final String NEW_ROLE = "OWNER";
    public static final String NEW_USERNAME = "Boba";
    public static final String NEW_PASSWORD = "Boba";
    public static final String NEW_EMAIL = "boba2@gmail.com";
    public static final String NEW_NAME = "Boba";
    public static final String NEW_SURNAME = "Milivojevic";
    public static final Date NEW_BIRTH_DATE = new Date();
    public static final String NEW_PHONE_NUMBER = "022889999";
    public static final String NEW_ADDRESS = "Puskinova 16";
    public static final String NEW_CITY = "Madrid";
    public static final String NEW_COUNTRY = "Spain";
    public static final String NEW_ACCOUNT_NUMBER = "5896325";
    public static final String NEW_IMAGE_URL = "images/img1.jpg";

    public static final String BAD_TYPE = "";
    public static final String BAD_ROLE = "";
    public static final String BAD_USERNAME = "";
    public static final String BAD_PASSWORD = "";
    public static final String BAD_EMAIL = "";
    public static final String BAD_NAME = "";
    public static final String BAD_SURNAME = "";
    public static final Date BAD_BIRTH_DATE = new Date();
    public static final String BAD_PHONE_NUMBER = "";
    public static final String BAD_ADDRESS = "";
    public static final String BAD_CITY = "";
    public static final String BAD_COUNTRY = "";
    public static final String BAD_ACCOUNT_NUMBER = "";
    public static final String BAD_IMAGE_URL = "";

    public static final int DB_O_NUMBER = 6;

    public static final Long DB_ID = 4L;

    public static final int DB_AD_NUMBER = 1;
    public static final Long DB_A_ID = 1L;

    public static final String DB_ADV_CURRENCY = Currency.RSD.name();
    public static final String DB_ADV_PHONE_NUMBER = "021400198";
    public static final double DB_ADV_PRICE = 10000d;
    public static final String DB_ADV_STATE = AdvertisementState.WAITING.name();
    public static final String DB_ADV_TITLE = "Add title";
    public static final String DB_ADV_TYPE = AdvertisementType.RENT.name();

    public static final Long DB_ADV_OWNER_ID = 4L;
    public static final Long DB_ADV_RS_ID = 2L;

    public static final String DB_TYPE = "SYS_ADMIN";
    public static final String DB_ROLE = Role.SYS_ADMIN.name();

    public static final String DB_USERNAME = "g";
    public static final String DB_PASSWORD = "$2a$10$2nUwyYdXRxhKio6aVrwsVOXlUqSs9XnsIndPiyT.3AphhvZ/UYBta";
    public static final String DB_EMAIL = "default@gmail.com";
    public static final String DB_NAME = "admin default";
    public static final String DB_SURNAME = "admin";
 //   public static final Date DB_BIRTH_DATE = new Date();
    public static final String DB_PHONE_NUMBER = "021 111 111";
    public static final String DB_ADDRESS = "Bul. Oslobodjenja 1";
    public static final String DB_CITY = "Novi Sad";
    public static final String DB_COUNTRY = "Serbia";
    public static final String DB_ACCOUNT_NUMBER = "0";
    public static final String DB_IMAGE_URL = "images/img1.jpg";



    /**
     * Empty constructor
     */
    public OwnerConstants() {
    }
}
