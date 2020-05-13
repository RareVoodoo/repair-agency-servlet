package ua.testing.repairagency.service;

import ua.testing.repairagency.dao.UserDao;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.User;
import ua.testing.repairagency.util.DBConnector;
import ua.testing.repairagency.util.PasswordEncryptor;

import java.util.List;

public class LoginService {
    private PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();

    public String authenticateUser(UserDto userDTO) {
        String userName = userDTO.getUsername();
        String password = userDTO.getPassword();

        try {

            UserDao userDao = new UserDao(DBConnector.getConnection());
            List<User> userList = userDao.getAll();


            for (User user : userList) {
                String userNameDB = user.getUsername();
                String passwordDB = user.getPassword();
                int roleID = user.getIdAuthority();


                if (userName.equals(userNameDB) && password.equals(passwordEncryptor.decrypt(passwordDB)) && roleID == 3)
                    return "Admin_Role";
                else if (userName.equals(userNameDB) && password.equals(passwordEncryptor.decrypt(passwordDB)) && roleID == 2)
                    return "Master_Role";
                else if (userName.equals(userNameDB) && password.equals(passwordEncryptor.decrypt(passwordDB)) && roleID == 1)
                    return "User_Role";
            }
        } catch (PersistException e) {
            e.printStackTrace();
        }

        return "Invalid user credentials";
    }
}
