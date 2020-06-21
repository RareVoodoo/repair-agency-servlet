package ua.testing.repairagency.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CancelRequestCommand implements Command
{
    private Logger logger = LogManager.getLogger();
    private RepairRequestService repairRequestService = new RepairRequestService();
    private RepairRequestDto requestDto = new RepairRequestDto();
    private FormValidator<RepairRequestDto> formValidator = new FormValidator<>();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        request.setAttribute(Constants.REDIRECT_ATTRIBUTE, true);

        logger.info(request.getParameter(Constants.ID_PARAM));
        requestDto.setId(Long.parseLong(request.getParameter(Constants.REQ_ID_PARAM)));
        requestDto.setCancellationReason(request.getParameter(Constants.CANCEL_REASON_PARAM));
        requestDto.setUsdPrice(Constants.REPAIR_PRICE_DEFAULT);
        requestDto.setUahPrice(Constants.REPAIR_PRICE_DEFAULT);


        if(formValidator.isValidFields(requestDto)) {
            repairRequestService.cancelUserRequest(requestDto);
        }

        session.setAttribute(Constants.ERRORS_ATTRIBUTE, formValidator.getErrors());

        return Constants.ADMIN_REDIRECT;


    }
}
