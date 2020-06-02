package ua.testing.repairagency.command.impl;


import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.service.RepairRequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class AcceptRequestCommand implements Command {
    private RepairRequestService repairRequestService = new RepairRequestService();
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        request.setAttribute("redirect", true);


        RepairRequestDto repairRequestDto = new RepairRequestDto();

        repairRequestDto.setId(Long.parseLong(request.getParameter("requestId")));
        repairRequestDto.setUsdPrice(Long.parseLong(request.getParameter("price")));


        repairRequestService.acceptUserRequest(repairRequestDto, session);

        return "/app/admin";
    }
}
