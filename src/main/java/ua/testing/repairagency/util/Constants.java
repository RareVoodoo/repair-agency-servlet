package ua.testing.repairagency.util;

import java.util.Locale;

public class Constants {
    public static final String REDIRECT_ATTRIBUTE = PropertyReader.getPropertyValue("attr.redirect");
    public static final String ROLE_ATTRIBUTE = PropertyReader.getPropertyValue("attr.role");
    public static final String CURRENT_USERNAME_ATTRIBUTE = PropertyReader.getPropertyValue("attr.current.username");
    public static final String REQUEST_ATTRIBUTE = PropertyReader.getPropertyValue("attr.request");
    public static final String COMMENT_ATTRIBUTE = PropertyReader.getPropertyValue("attr.comment");
    public static final String CURRENT_LOCALE_ATTRIBUTE = PropertyReader.getPropertyValue("attr.current.locale");
    public static final String LANGUAGE_ATTRIBUTE = PropertyReader.getPropertyValue("attr.language");
    public static final String NUMBER_OF_PAGES_ATTRIBUTE = PropertyReader.getPropertyValue("attr.no.of.pages");
    public static final String CURRENT_PAGE_ATTRIBUTE = PropertyReader.getPropertyValue("attr.current.page");
    public static final String RECORDS_PER_PAGE_ATTRIBUTE = PropertyReader.getPropertyValue("attr.records.per.page");


    public static final String REQUEST_ID_PARAM = PropertyReader.getPropertyValue("param.request.id");
    public static final String PRICE_PARAM = PropertyReader.getPropertyValue("param.price");
    public static final String USERNAME_PARAM =  PropertyReader.getPropertyValue("param.username");
    public static final String PASSWORD_PARAM =  PropertyReader.getPropertyValue("param.password");
    public static final String ID_PARAM =  PropertyReader.getPropertyValue("param.id");
    public static final String REQ_ID_PARAM =  PropertyReader.getPropertyValue("param.req.id");
    public static final String CANCEL_REASON_PARAM = PropertyReader.getPropertyValue("param.cancel.reason");
    public static final String DESCRIPTION_PARAM = PropertyReader.getPropertyValue("param.description");
    public static final String PHONE_NUMBER_PARAM = PropertyReader.getPropertyValue("param.phone.number");
    public static final String ADDRESS_PARAM = PropertyReader.getPropertyValue("param.address");
    public static final String FULL_NAME_PARAM = PropertyReader.getPropertyValue("param.full.name");
    public static final String LANG_PARAM = PropertyReader.getPropertyValue("param.lang");
    public static final String CURRENT_PAGE_PARAM = PropertyReader.getPropertyValue("param.current.page");


    public static final String ADMIN_REDIRECT = PropertyReader.getPropertyValue("redirect.admin");
    public static final String USER_REDIRECT = PropertyReader.getPropertyValue("redirect.user");
    public static final String MASTER_REDIRECT = PropertyReader.getPropertyValue("redirect.master");
    public static final String LOGIN_REDIRECT = PropertyReader.getPropertyValue("redirect.login");

    public static final String ADMIN_PAGE_PATH = PropertyReader.getPropertyValue("path.admin");
    public static final String MASTER_PAGE_PATH = PropertyReader.getPropertyValue("path.master");
    public static final String USER_PAGE_PATH = PropertyReader.getPropertyValue("path.user");
    public static final String LOGIN_PAGE_PATH = PropertyReader.getPropertyValue("path.login");
    public static final String REGISTRATION_PAGE_PATH = PropertyReader.getPropertyValue("path.registration");

    public static final String ILLEGAL_ACCESS_ERROR_PATH =
            PropertyReader.getPropertyValue("path.error.illegal.access");


    public static final String ADMIN_ROLE = "Admin";
    public static final String MASTER_ROLE = "Master";
    public static final String USER_ROLE = "User";
    public static final String UNKNOWN_ROLE = "Unknown";

    public static final String CONTENT_TYPE_DEFAULT = "text/html;";
    public static final String CHAR_ENCODING_DEFAULT = "UTF-8";

    public static final String UA_LANGUAGE = "ua";
    public static final String EN_LANGUAGE = "en";

    public static final Locale UA_LOCALE = new Locale("ua");
    public static final Locale EN_LOCALE = Locale.US;



    public static final int SESSION_MAX_INACTIVE_INTERVAL = 900;

    public static final String ENCRYPTOR_PASSWORD = "G&^T&!S==1=s&Y&*Jik";

    public static final int USER_ROLE_ID = 1;

    public static final String POOL_CONNECTION_CONTEXT_PATH = "java:comp/env/jdbc/mysqlPool";

    public static final String CYRILLIC_TO_LATIN = "Ukrainian-Latin/BGN";
    public static final String DEFAULT_PROPERTY_FILENAME = "consts.properties";

    public static final long REPAIR_PRICE_DEFAULT = 0L;
    public static final String CANCELLATION_REASON_DEFAULT = "not canceled";
    public static final int NUMBER_OF_PAGES_DEFAULT = 3;
    public static final int CURRENT_PAGE_DEFAULT = 1;

    public final static long CURRENCY_RATE_CONSTANT =  30;
}
