package kts.project.constants;

import kts.project.model.enumerations.Role;

import java.util.Date;

/**
 * Created by Sandra on 7/18/2017.
 */
public class CompanyConstants {

    //owner id=4
    public static final String USERNAME = "a";
    public static final String PASSWORD = "a";

    public static final String USERNAME_ADMIN = "admin";
    public static final String PASSWORD_ADMIN = "admin";



    public static final int DB_COMPANY_NUMBER = 3;
    public static final int DB_UNAPPROVED_COMPANY_NUMBER = 1;

    public static final String NEW_COMPANY_TYPE = "Company";
    public static final String NEW_COMPANY_ROLE = Role.OWNER.name();
    public static final String NEW_COMPANY_USERNAME = "NewCompanyUsername";
    public static final String NEW_COMPANY_PASSWORD = "NewCompanyUsername";
    public static final String NEW_COMPANY_EMAIL = "newcompanyusername@gmail.com";
    public static final String NEW_COMPANY_NAME = "NewCompanyUsername";
    public static final String NEW_COMPANY_SURNAME = "NewCompanyUsername";
    public static final Date NEW_COMPANY_BIRTH_DATE = new Date();
    public static final String NEW_COMPANY_PHONE_NUMBER = "123456789";
    public static final String NEW_COMPANY_ADDRESS = "Mise Dimitrijevica 6.";
    public static final String NEW_COMPANY_CITY = "Novi Sad";
    public static final String NEW_COMPANY_COUNTRY = "Srbija";
    public static final String NEW_COMPANY_ACCOUNT_NUMBER = "123456789987456321";
    public static final String NEW_COMPANY_IMAGE_URL = "images/img1.png";
    public static final String NEW_COMPANY_PIB = "new pib";
    public static final String NEW_COMPANY_SITE = "www.newcompany.com";
    public static final String NEW_COMPANY_FAX = "123456789";


    //invalid input
    public static final String NEW_COMPANY_USERNAME_EXIST = "b";
    public static final String NEW_COMPANY_PIB_EXIST = "PIB1";
    public static final String NEW_COMPANY_EMAIL_EMPTY = "";
    public static final String NEW_COMPANY_USERNAME_EMPTY = "";
    public static final String NEW_COMPANY_PASSWORD_EMPTY = "";
    public static final String NEW_COMPANY_PIB_EMPTY = "";


    public static final Long DB_COMPANY_ID = 5L;
    public static final String DB_COMPANY_ROLE = Role.OWNER.name();
    public static final String DB_COMPANY_USERNAME = "b";
    public static final String DB_COMPANY_EMAIL = "sandra1@gmail.com";
    public static final String DB_COMPANY_NAME = "sandra";
    public static final String DB_COMPANY_SURNAME = "Jaimez";
    public static final Date DB_COMPANY_BIRTH_DATE = new Date();
    public static final String DB_COMPANY_PHONE_NUMBER = "021 333 555";
    public static final String DB_COMPANY_ADDRESS = "Jovana Ducica 12";
    public static final String DB_COMPANY_CITY = "Beograd";
    public static final String DB_COMPANY_COUNTRY = "Serbia";
    public static final String DB_COMPANY_ACCOUNT_NUMBER = "59856";
    public static final String DB_COMPANY_IMAGE_URL = "images/img2.jpg";
    public static final String DB_COMPANY_PIB = "PIB1";
    public static final String DB_COMPANY_SITE = "nista";
    public static final String DB_COMPANY_FAX = "fax259945121";

    //unapproved
    public static final Long DB_COMPANY_ID_UNAPPROVED = 15L;
    public static final String DB_COMPANY_ROLE_UNAPPROVED = Role.OWNER.name();
    public static final String DB_COMPANY_USERNAME_UNAPPROVED = "bb";
    public static final String DB_COMPANY_EMAIL_UNAPPROVED = "sandra1@gmail.com";
    public static final String DB_COMPANY_NAME_UNAPPROVED = "nina";
    public static final String DB_COMPANY_SURNAME_UNAPPROVED = "simic";
    public static final String DB_COMPANY_PHONE_NUMBER_UNAPPROVED = "021 333 555";
    public static final String DB_COMPANY_ADDRESS_UNAPPROVED = "Jovana Ducica 12";
    public static final String DB_COMPANY_CITY_UNAPPROVED = "Beograd";
    public static final String DB_COMPANY_COUNTRY_UNAPPROVED = "Serbia";
    public static final String DB_COMPANY_ACCOUNT_NUMBER_UNAPPROVED = "59856";
    public static final String DB_COMPANY_IMAGE_URL_UNAPPROVED = "images/img2.jpg";
    public static final String DB_COMPANY_PIB_UNAPPROVED = "PIB2";
    public static final String DB_COMPANY_SITE_UNAPPROVED = "nesto";
    public static final String DB_COMPANY_FAX_UNAPPROVED = "fax252222222";


}
