package ua.testing.repairagency.service;

import ua.testing.repairagency.dao.impl.UserDao;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.User;
import ua.testing.repairagency.region.transliteration.NameTransliteration;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.DbConnector;
import ua.testing.repairagency.util.PasswordEncryptor;

import java.sql.Connection;


public class RegisterService {

    private PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
    private NameTransliteration nameTransliteration = new NameTransliteration();
    private Connection connection = DbConnector.getInstance().getConnection();


    /**
     * Registers new user
     * @param userDto user object with user credentials
     */
    public void registerUser(UserDto userDto){
        userDto.setPassword(passwordEncryptor.encrypt(userDto.getPassword()));

        try {
            createNewUser(userDto);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }



    /**
     * Adds user object to the database
     * @param userDto user object with user credentials
     */
    public void createNewUser(UserDto userDto) throws PersistException {
        UserDao userDao = new UserDao(connection);

        try {
        userDao.create(new User.Builder()
                .en_name(nameTransliteration.transliterate(Constants.EN_LOCALE, userDto.getEn_name()))
                .ua_name(nameTransliteration.transliterate(Constants.UA_LOCALE, userDto.getEn_name()))
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .enabled(true)
                .idAuthority(Constants.USER_ROLE_ID)
                .build());

        }catch (Exception e){
            throw new PersistException(e);
        }
    }


}
