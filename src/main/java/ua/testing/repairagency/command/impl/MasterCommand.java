package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dao.impl.RepairRequestDao;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.util.DbConnector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MasterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RepairRequestService repairRequestService = new RepairRequestService();
        RepairRequestDao requestDao = new RepairRequestDao(DbConnector.getInstance().getConnection());

        if (session.getAttribute("role").equals("Master")) {
            try {
                String username = (String) session.getAttribute("currentUsername");
                request.setAttribute("request", requestDao.getAllAcceptedRequests());
            } catch (PersistException e) {
                e.printStackTrace();
            }
            return "/WEB-INF/view/Master.jsp";
        } else {
            return "/WEB-INF/view/error/IllegalAccessError.jsp";
        }
    }
}
