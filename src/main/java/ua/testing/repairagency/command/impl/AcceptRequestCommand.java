package ua.testing.repairagency.command.impl;


import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.PropertyReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class AcceptRequestCommand implements Command {
    private RepairRequestService repairRequestService = new RepairRequestService();


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        request.setAttribute(Constants.REDIRECT_ATTRIBUTE, true);

        RepairRequestDto repairRequestDto = new RepairRequestDto();

        repairRequestDto.setId(Long.parseLong(request.getParameter(Constants.REQUEST_ID_PARAM)));
        repairRequestDto.setUsdPrice(Long.parseLong(request.getParameter(Constants.PRICE_PARAM)));

        repairRequestService.acceptUserRequest(repairRequestDto, session);

        return Constants.ADMIN_PAGE_PATH;
    }
}
