package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dao.impl.RepairRequestDao;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.DbConnector;
import ua.testing.repairagency.util.PropertyReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MasterCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RepairRequestDao requestDao = new RepairRequestDao(DbConnector.getInstance().getConnection());

        if (session.getAttribute(Constants.ROLE_ATTRIBUTE).equals(Constants.MASTER_ROLE)) {
            try {
                String username = (String) session.getAttribute(Constants.CURRENT_USERNAME_ATTRIBUTE);
                request.setAttribute(Constants.REQUEST_ATTRIBUTE, requestDao.getAllAcceptedRequests());
            } catch (PersistException e) {
                e.printStackTrace();
            }
            return Constants.MASTER_PAGE_PATH;
        } else {
            return Constants.ILLEGAL_ACCESS_ERROR_PATH;
        }
    }
}
