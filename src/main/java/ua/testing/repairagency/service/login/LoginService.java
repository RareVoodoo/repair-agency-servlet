package ua.testing.repairagency.service.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.testing.repairagency.dao.impl.UserDao;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.User;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.DbConnector;
import ua.testing.repairagency.util.PasswordEncryptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public class LoginService {
    private PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
    private HashMap<Integer, AuthorityAuthentication> userAuthorities = new HashMap<>();
    private Logger logger = LogManager.getLogger();
    private HttpSession session;
    private Connection connection = DbConnector.getInstance().getConnection();


    {
        userAuthorities.put(3, this::loadAdmin);
        userAuthorities.put(2, this::loadMaster);
        userAuthorities.put(1, this::loadUser);
    }

    /**
     * Authenticates user with his credentials
     * @param userDto User`s object with his credentials
     * @param request current HttpServletRequest
     * @return User's page
     */
    public String authenticateUser(UserDto userDto, HttpServletRequest request) {
        request.setAttribute("redirect", true);

        String userName = userDto.getUsername();
        String password = userDto.getPassword();


        try {

            UserDao userDao = new UserDao(connection);
            User user = userDao.getUserByUsername(userName).get();

            if (passwordEncryptor.decrypt(user.getPassword()).equals(password)){
                String userPage = userAuthorities.get(user.getIdAuthority()).authenticate(request, user);
                return userPage;
            }


        } catch (PersistException e) {
            logger.error("Invalid User credentials");
        } finally {
            request.getSession().setAttribute("currentUsername", userName);
        }

        return "/app/login";
    }

    /**
     * Loads user with authority "USER"
     * @param request current HttpServletRequest
     * @param user User`s object with his credentials
     * @return "USER" page
     */
    private String loadUser(HttpServletRequest request, User user) {
        logger.info("User's Page");
        loadSession(request);
        request.setAttribute("userName", user.getUsername());
        session.setAttribute("role", "User");
        return "/app/user";
    }

    /**
     * Loads user with authority "MASTER"
     * @param request current HttpServletRequest
     * @param user User`s object with his credentials
     * @return "MASTER" page
     */
    private String loadMaster(HttpServletRequest request, User user) {
        logger.info("Master's Page");
        loadSession(request);
        request.setAttribute("userName", user.getUsername());
        session.setAttribute("role", "Master");
        return "/app/master";
    }


    /**
     * Loads user with authority "ADMIN"
     * @param request current HttpServletRequest
     * @param user User`s object with his credentials
     * @return "ADMIN" page
     */
    private String loadAdmin(HttpServletRequest request, User user) {
        loadSession(request);
        logger.info("Admin's Page");
        request.setAttribute("userName", user.getUsername());
        session.setAttribute("role", "Admin");
        return "/app/admin";
    }

    /**
     * Loads current session and sets session inactive interval
     * @param request current HttpServletRequest
     */
    private void loadSession(HttpServletRequest request){
        this.session = request.getSession();
        this.session.setMaxInactiveInterval(Constants.SESSION_MAX_INACTIVE_INTERVAL);
    }

}
