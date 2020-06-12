package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.PropertyReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminCommand implements Command {
    private RepairRequestService repairRequestService = new RepairRequestService();


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();


        if (session.getAttribute(Constants.ROLE_ATTRIBUTE).equals(Constants.ADMIN_ROLE)) {
            repairRequestService.paginatePage(request);
            return Constants.ADMIN_PAGE_PATH;
        } else {
            return Constants.ILLEGAL_ACCESS_ERROR_PATH;
        }
    }
}
