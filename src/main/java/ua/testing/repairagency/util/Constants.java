package ua.testing.repairagency.util;

import java.util.Locale;

public class Constants {
    public static final String REDIRECT_ATTRIBUTE = PropertyReader.getConstPropertyValue("attr.redirect");
    public static final String ROLE_ATTRIBUTE = PropertyReader.getConstPropertyValue("attr.role");
    public static final String CURRENT_USERNAME_ATTRIBUTE = PropertyReader.getConstPropertyValue("attr.current.username");
    public static final String REQUEST_ATTRIBUTE = PropertyReader.getConstPropertyValue("attr.request");
    public static final String COMMENT_ATTRIBUTE = PropertyReader.getConstPropertyValue("attr.comment");
    public static final String CURRENT_LOCALE_ATTRIBUTE = PropertyReader.getConstPropertyValue("attr.current.locale");
    public static final String LANGUAGE_ATTRIBUTE = PropertyReader.getConstPropertyValue("attr.language");
    public static final String NUMBER_OF_PAGES_ATTRIBUTE = PropertyReader.getConstPropertyValue("attr.no.of.pages");
    public static final String CURRENT_PAGE_ATTRIBUTE = PropertyReader.getConstPropertyValue("attr.current.page");
    public static final String RECORDS_PER_PAGE_ATTRIBUTE = PropertyReader.getConstPropertyValue("attr.records.per.page");
    public static final String ERRORS_ATTRIBUTE = PropertyReader.getConstPropertyValue("attr.errors");



    public static final String REQUEST_ID_PARAM = PropertyReader.getConstPropertyValue("param.request.id");
    public static final String PRICE_PARAM = PropertyReader.getConstPropertyValue("param.price");
    public static final String USERNAME_PARAM =  PropertyReader.getConstPropertyValue("param.username");
    public static final String PASSWORD_PARAM =  PropertyReader.getConstPropertyValue("param.password");
    public static final String ID_PARAM =  PropertyReader.getConstPropertyValue("param.id");
    public static final String REQ_ID_PARAM =  PropertyReader.getConstPropertyValue("param.req.id");
    public static final String CANCEL_REASON_PARAM = PropertyReader.getConstPropertyValue("param.cancel.reason");
    public static final String DESCRIPTION_PARAM = PropertyReader.getConstPropertyValue("param.description");
    public static final String PHONE_NUMBER_PARAM = PropertyReader.getConstPropertyValue("param.phone.number");
    public static final String ADDRESS_PARAM = PropertyReader.getConstPropertyValue("param.address");
    public static final String FULL_NAME_PARAM = PropertyReader.getConstPropertyValue("param.full.name");
    public static final String LANG_PARAM = PropertyReader.getConstPropertyValue("param.lang");
    public static final String CURRENT_PAGE_PARAM = PropertyReader.getConstPropertyValue("param.current.page");

    public static final String ADMIN_REDIRECT = PropertyReader.getConstPropertyValue("redirect.admin");
    public static final String USER_REDIRECT = PropertyReader.getConstPropertyValue("redirect.user");
    public static final String MASTER_REDIRECT = PropertyReader.getConstPropertyValue("redirect.master");
    public static final String LOGIN_REDIRECT = PropertyReader.getConstPropertyValue("redirect.login");

    public static final String ADMIN_PAGE_PATH = PropertyReader.getConstPropertyValue("path.admin");
    public static final String MASTER_PAGE_PATH = PropertyReader.getConstPropertyValue("path.master");
    public static final String USER_PAGE_PATH = PropertyReader.getConstPropertyValue("path.user");
    public static final String LOGIN_PAGE_PATH = PropertyReader.getConstPropertyValue("path.login");
    public static final String REGISTRATION_PAGE_PATH = PropertyReader.getConstPropertyValue("path.registration");

    public static final String ILLEGAL_ACCESS_ERROR_PATH =
            PropertyReader.getConstPropertyValue("path.error.illegal.access");


    public static final String USERNAME_VALIDATION_PROPERTY = "size.userForm.username";
    public static final String COMMENT_VALIDATION_PROPERTY = "form.comment";
    public static final String PASSWORD_VALIDATION_PROPERTY = "size.userForm.password";
    public static final String DESCRIPTION_VALIDATION_PROPERTY = "size.userForm.password";
    public static final String ADDRESS_VALIDATION_PROPERTY = "form.validate.address";
    public static final String PHONE_NUMBER_VALIDATION_PROPERTY = "form.validate.phone.number";
    public static final String EMPTY_PRICE_VALIDATION_PROPERTY = "form.validation.price.null.or.char";
    public static final String POSITIVE_PRICE_VALIDATION_PROPERTY = "form.validation.price.null.or.char";
    public static final String CANCELLATION_REASON_VALIDATION_PROPERTY = "form.validation.cancellation.reason";


    public static final String PASSWORD_VALIDATION_REGEX = "^(?=.*[0-9]).{8,15}$";
    public static final String USERNAME_VALIDATION_REGEX = "([A-Za-z@_-]\\d*){1,20}";
    public static final String COMMENT_VALIDATION_REGEX = "^.([^\"]){1,449}$";
    public static final String DESCRIPTION_VALIDATION_REGEX = "^.([^\"]){1,150}$";
    public static final String ADDRESS_VALIDATION_REGEX = "^[\\w\\s',-\\\\/.()#]{1,75}$";
    public static final String PHONE_NUMBER_VALIDATION_REGEX = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]{1,16}$";
    public static final String PRICE_VALIDATION_REGEX = "^\\d+?$";
    public static final String CANCELLATION_REASON_VALIDATION_REGEX = "^.([^\"]){1,100}$";




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
    public static final String CONSTS_PROPERTY_FILENAME = "consts.properties";
    public static final String MESSAGES_PROPERTY_FILENAME = "messages.properties";


    public static final long REPAIR_PRICE_DEFAULT = 0L;
    public static final String CANCELLATION_REASON_DEFAULT = "not canceled";
    public static final int NUMBER_OF_PAGES_DEFAULT = 3;
    public static final int CURRENT_PAGE_DEFAULT = 1;

    public final static long CURRENCY_RATE_CONSTANT =  30;
    public static final String PRICE_MAX_VALUE = "9999";

}
