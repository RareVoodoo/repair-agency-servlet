package ua.testing.repairagency.command.impl;


import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.FormValidator;
import ua.testing.repairagency.util.PropertyReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


public class AcceptRequestCommand implements Command {
    private RepairRequestService repairRequestService = new RepairRequestService();
    private FormValidator<RepairRequestDto> formValidator = new FormValidator<>();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        request.setAttribute(Constants.REDIRECT_ATTRIBUTE, true);

        RepairRequestDto repairRequestDto = new RepairRequestDto();
        final String priceParamValue = request.getParameter(Constants.PRICE_PARAM);

        if(priceParamValue.matches(Constants.PRICE_VALIDATION_REGEX)) {
            Long price = Long.parseLong(priceParamValue);

            repairRequestDto.setId(Long.parseLong(request.getParameter(Constants.REQUEST_ID_PARAM)));
            repairRequestDto.setUsdPrice(price);

        }

        if (formValidator.isValidFields(repairRequestDto)) {
            repairRequestService.acceptUserRequest(repairRequestDto, session);
        }

        session.setAttribute(Constants.ERRORS_ATTRIBUTE, formValidator.getErrors());


        return Constants.ADMIN_REDIRECT;
    }
}
