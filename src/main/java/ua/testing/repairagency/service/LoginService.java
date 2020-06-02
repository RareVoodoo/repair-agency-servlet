package ua.testing.repairagency.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.testing.repairagency.dao.impl.UserDao;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.User;
import ua.testing.repairagency.util.DbConnector;
import ua.testing.repairagency.util.PasswordEncryptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public class LoginService {
    private PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
    private HashMap userRoles = new HashMap();
    Logger logger = LogManager.getLogger();
    private HttpSession session;


    public String authenticateUser(UserDto userDto, HttpServletRequest request) {
        request.setAttribute("redirect", true);


        String userName = userDto.getUsername();
        String password = userDto.getPassword();



        try {

            UserDao userDao = new UserDao(DbConnector.getInstance().getConnection());
            List<User> userList = userDao.getAllById();

            for (User user : userList) {
                String userNameDB = user.getUsername();
                String passwordDB = user.getPassword();
                int roleID = user.getIdAuthority();


                if (userName.equals(userNameDB) && password.equals(passwordEncryptor.decrypt(passwordDB)) && roleID == 3)
                    return loadAdmin(userDto, request);
                else if (userName.equals(userNameDB) && password.equals(passwordEncryptor.decrypt(passwordDB)) && roleID == 2)
                    return loadMaster(userDto, request);
                else if (userName.equals(userNameDB) && password.equals(passwordEncryptor.decrypt(passwordDB)) && roleID == 1)
                    return loadUser(userDto, request);

            }
        } catch (PersistException e) {
            e.printStackTrace();
        } finally {
            request.getSession().setAttribute("currentUsername", userName);
        }

        return "/app/login";
    }

    private void isRegisteredUser(String login, String pass){

    }

    private String loadUser(UserDto userDto, HttpServletRequest request) {
        logger.info("User's Page");
        loadSession(request);
        request.setAttribute("userName", userDto.getUsername());
        session.setAttribute("role", "User");
        return "/app/user";
    }

    private String loadMaster(UserDto userDto, HttpServletRequest request) {
        logger.info("Master's Page");
        loadSession(request);
        request.setAttribute("userName", userDto.getUsername());
        session.setAttribute("role", "Master");
        return "/app/master";
    }

    private String loadAdmin(UserDto userDto, HttpServletRequest request) {
        loadSession(request);
        logger.info("Admin's Page");
        request.setAttribute("userName", userDto.getUsername());
        session.setAttribute("role", "Admin");
        return "/app/admin";
    }


    private void loadSession(HttpServletRequest request){
        session = request.getSession();
        session.setMaxInactiveInterval(10 * 60);
    }

}
