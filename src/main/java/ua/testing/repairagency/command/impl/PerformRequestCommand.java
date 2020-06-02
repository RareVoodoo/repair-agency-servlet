package ua.testing.repairagency.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.service.RepairRequestService;


import javax.servlet.http.HttpServletRequest;


public class PerformRequestCommand implements Command {
    private RepairRequestService repairRequestService = new RepairRequestService();
    private RepairRequestDto requestDto = new RepairRequestDto();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("redirect", true);

        requestDto.setId(Long.parseLong(request.getParameter("requestId")));

        repairRequestService.performUserRequest(requestDto);
        return "/app/master";
    }
}
