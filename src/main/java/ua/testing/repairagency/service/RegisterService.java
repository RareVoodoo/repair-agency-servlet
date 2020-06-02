package ua.testing.repairagency.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.testing.repairagency.dao.impl.UserDao;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.User;
import ua.testing.repairagency.region.transliteration.NameTransliteration;
import ua.testing.repairagency.util.DbConnector;
import ua.testing.repairagency.util.PasswordEncryptor;

import javax.servlet.http.HttpServletRequest;


public class RegisterService {
    private static final int USER_ROLE_ID = 1;
    private PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
    private NameTransliteration nameTransliteration = new NameTransliteration();


    public void registerUser(UserDto userDto){


        userDto.setPassword(passwordEncryptor.encrypt(userDto.getPassword()));

        try {
            createNewUser(userDto);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    public void createNewUser(UserDto userDto) throws PersistException {

        final Logger logger = LoggerFactory.getLogger(RegisterService.class);

        UserDao userDao = new UserDao(DbConnector.getInstance().getConnection());

        try {
        userDao.create(new User.Builder()
                .en_name(nameTransliteration.transliterate(NameTransliteration.EN_LOCALE, userDto.getEn_name()))
                .ua_name(nameTransliteration.transliterate(NameTransliteration.UA_LOCALE, userDto.getEn_name()))
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .enabled(true)
                .idAuthority(USER_ROLE_ID)
                .build());

        }catch (Exception e){
            throw new PersistException(e);
        }
    }


}
