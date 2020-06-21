package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dao.impl.CommentDao;
import ua.testing.repairagency.dao.impl.RepairRequestDao;
import ua.testing.repairagency.dto.CommentDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.DbConnector;
import ua.testing.repairagency.util.PropertyReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public class UserCommand implements Command {
    private Connection connection = DbConnector.getInstance().getConnection();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RepairRequestDao requestDao = new RepairRequestDao(connection);
        CommentDao commentDao = new CommentDao(connection);

        Object sessionValidationErrors =  session.getAttribute(Constants.ERRORS_ATTRIBUTE);
        request.setAttribute(Constants.ERRORS_ATTRIBUTE, sessionValidationErrors);

        if (session.getAttribute(Constants.ROLE_ATTRIBUTE).equals(Constants.USER_ROLE)) {
            try {
                String username = (String) session.getAttribute(Constants.CURRENT_USERNAME_ATTRIBUTE);
                request.setAttribute(Constants.REQUEST_ATTRIBUTE, requestDao.getAllByUserId(username));
                request.setAttribute(Constants.COMMENT_ATTRIBUTE, commentDao.getAllById());
            } catch (PersistException e) {
                e.printStackTrace();
            }
            return Constants.USER_PAGE_PATH;
        } else {
            return Constants.ILLEGAL_ACCESS_ERROR_PATH;
        }
    }
}
