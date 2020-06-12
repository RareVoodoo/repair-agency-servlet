package ua.testing.repairagency.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.util.Constants;

import javax.servlet.http.HttpServletRequest;

public class CancelRequestCommand implements Command
{
    private Logger logger = LogManager.getLogger();
    private RepairRequestService repairRequestService = new RepairRequestService();
    private RepairRequestDto requestDto = new RepairRequestDto();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(Constants.REDIRECT_ATTRIBUTE, true);

        logger.info(request.getParameter(Constants.ID_PARAM));
        requestDto.setId(Long.parseLong(request.getParameter(Constants.REQ_ID_PARAM)));
        requestDto.setCancellationReason(request.getParameter(Constants.CANCEL_REASON_PARAM));


        repairRequestService.cancelUserRequest(requestDto);
        return Constants.ADMIN_REDIRECT;
    }
}
