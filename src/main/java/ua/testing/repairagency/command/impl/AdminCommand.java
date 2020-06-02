package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.service.RepairRequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminCommand implements Command {

    private RepairRequestService repairRequestService = new RepairRequestService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();


        if (session.getAttribute("role").equals("Admin")) {
            repairRequestService.paginatePage(request);
            return "/WEB-INF/view/Admin.jsp";
        } else {
            return "/WEB-INF/view/error/IllegalAccessError.jsp";
        }
    }
}
