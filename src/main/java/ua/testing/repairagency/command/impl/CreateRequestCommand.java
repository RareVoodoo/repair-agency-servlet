package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateRequestCommand implements Command {
    private RepairRequestService requestService = new RepairRequestService();
    private RepairRequestDto requestDto = new RepairRequestDto();
    private FormValidator<RepairRequestDto> formValidator = new FormValidator<>();
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


        if(formValidator.isValidFields(requestDto)) {
            try {
                requestService.createNewRepairRequest(requestDto);
            } catch (PersistException e) {
                e.printStackTrace();
            }
        }
        session.setAttribute(Constants.ERRORS_ATTRIBUTE, formValidator.getErrors());

        return Constants.USER_REDIRECT;
    }
}
