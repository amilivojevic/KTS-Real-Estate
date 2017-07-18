package kts.project.constants;

/**
 * Created by Nina on 17-Jul-17.
 */

import kts.project.model.enumerations.HeatingType;
import kts.project.model.enumerations.RealEstateType;

import java.util.Date;

/**
 * Constants for RealEstate tests
 *
 */
public class RealEstateConstants {

    public static final String NEW_DESCRIPTION = "NOVI BLA";
    public static final String NEW_IMAGE_URL = "images/img1.jpg";
    public static final boolean NEW_FURNITURE = true;
    public static final boolean NEW_PARKING = false;
    public static final double NEW_AREA = 300d;
    public static final String NEW_CONSTRUCTION_YEAR = "2000";
    public static final int NEW_ROOMS_NUMBER = 2;
    public static final int NEW_BATHROOMS_NUMBER = 1;
    public static final String NEW_CITY = "Beograd";
    public static final String NEW_CITY_AREA = "Banovo Brdo";
    public static final String NEW_STREET = "Nova ulica";
    public static final String NEW_STREET_NUMBER = "10";
    public static final String NEW_STATE = "Serbia";
    public static final String NEW_ZIP_CODE = "11000";
    public static final String NEW_HEATING_TYPE = "CENTRAL";
    public static final String NEW_RS_TYPE = "HOUSE";

    public static final String USERNAME = "a";
    public static final String PASSWORD = "a";

    public static final String BAD_DESCRIPTION = "";
    public static final String BAD_IMAGE_URL = "";
    public static final boolean BAD_FURNITURE = true;
    public static final boolean BAD_PARKING = false;
    public static final double BAD_AREA = -1d;
    public static final String BAD_CONSTRUCTION_YEAR = "";
    public static final int BAD_ROOMS_NUMBER = -2;
    public static final int BAD_BATHROOMS_NUMBER = -1;
    public static final String BAD_CITY = "";
    public static final String BAD_CITY_AREA = "";
    public static final String BAD_STREET = "";
    public static final String BAD_STREET_NUMBER = "";
    public static final String BAD_STATE = "";
    public static final String BAD_ZIP_CODE = "";
    public static final String BAD_HEATING_TYPE = null;
    public static final String BAD_RS_TYPE = null;

    public static final int DB_ADV_NUMBER = 1;
    public static final Long DB_ID = 2L;
    public static final Long BAD_ID = 5L;

    public static final String DB_DESCRIPTION = "xfbnz";
    public static final String DB_IMAGE_URL = "images/img1.jpg";
    public static final boolean DB_FURNITURE = true;
    public static final boolean DB_PARKING = true;
    public static final double DB_AREA = 0d;
    public static final String DB_CONSTRUCTION_YEAR ="adsf";
    public static final int DB_ROOMS_NUMBER = 1;
    public static final int DB_BATHROOMS_NUMBER = 1;
    public static final Long DB_LOCATION_ID = 1L;
    public static final Long DB_OWNER_ID = 4L;
    public static final String DB_HEATING_TYPE = HeatingType.CENTRAL.name();
    public static final String DB_RS_TYPE = RealEstateType.HOUSE.name();

    /**
     * Empty constructor
     */
    public RealEstateConstants() {
    }
}
