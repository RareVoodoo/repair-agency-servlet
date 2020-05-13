package ua.testing.repairagency.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.testing.repairagency.dao.UserDao;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.User;
import ua.testing.repairagency.util.DBConnector;


public class RegisterService {
    public void createNewUser(UserDto userDTO) throws PersistException {
        final Logger logger = LoggerFactory.getLogger(RegisterService.class);

        UserDao userDao = new UserDao(DBConnector.getConnection());

        try {
        userDao.create(new User.Builder()
                .en_name(userDTO.getEn_name())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .enabled(true)
                .idAuthority(1)
                .build());

        }catch (Exception e){
            throw new PersistException(e);
        }
    }
}
