package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateRequestCommand implements Command {
    private RepairRequestService requestService = new RepairRequestService();
    private RepairRequestDto requestDto = new RepairRequestDto();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String description = request.getParameter(Constants.DESCRIPTION_PARAM);
        String userPhoneNumber = request.getParameter(Constants.PHONE_NUMBER_PARAM);
        String address = request.getParameter(Constants.ADDRESS_PARAM);


        requestDto.setDescription(description);
        requestDto.setUserPhoneNumber(userPhoneNumber);
        requestDto.setAddress(address);
        requestDto.setUsername((String) session.getAttribute(Constants.CURRENT_USERNAME_ATTRIBUTE));

        try {
            requestService.createNewRepairRequest(requestDto);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return Constants.USER_REDIRECT;
    }
}
