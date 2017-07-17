package kts.project.constants;

import kts.project.model.enumerations.AdvertisementState;
import kts.project.model.enumerations.AdvertisementType;

import java.util.Date;

/**
 * Created by Sandra on 7/17/2017.
 */
public class AdvertisementConstants {


    public static final String NEWADV_TITLE = "New title";
    public static final float NEWADV_PRICE = 100;
    public static final Date NEWADV_ENDING_DATE = new Date();
    public static final String NEWADV_PHONE_NUMBER = "123456789";
    public static final String NEWADV_TYPE = "SELL";
    public static final long NEWADV_RS_ID = 2L;
    public static final String NEWADV_CURRENCY = "EUR";

    public static final String USERNAME = "a";
    public static final String PASSWORD = "a";

    public static final String NEWADV_TITLE_BAD = "";
    public static final float NEWADV_PRICE_BAD = -100;
    public static final Date NEWADV_ENDING_DATE_BAD = null;
    public static final String NEWADV_PHONE_NUMBER_BAD = "";
    public static final String NEWADV_TYPE_BAD = "";
    public static final long NEWADV_RS_ID_BAD = 100L;
    public static final String NEWADV_CURRENCY_BAD = "";

    public static final int DB_ADV_NUMBER = 1;

    public static final Long DB_ADV_ID = 1L;
    //public static final Date DB_ADV_ANNOUNCEMENT_DATE = new Date("2001-01-20 17:00:00");
    public static final String DB_ADV_CURRENCY = kts.project.model.enumerations.Currency.RSD.name();
    //public static final Date DB_ADV_ENDING_DATE = new Date("2001-06-20 17:00:00");
    public static final String DB_ADV_PHONE_NUMBER = "021400198";
    public static final double DB_ADV_PRICE = 10000d;
    public static final AdvertisementState DB_ADV_STATE = AdvertisementState.WAITING;
    public static final String DB_ADV_TITLE = "Add title";
    public static final AdvertisementType DB_ADV_TYPE = AdvertisementType.RENT;
    //public static final Date DB_ADV_UPDATE_DATE = new Date("2002-02-20 18:00:00");;
    public static final Long DB_ADV_OWNER_ID = 4L;
    public static final Long DB_ADV_RS_ID = 2L;

}
