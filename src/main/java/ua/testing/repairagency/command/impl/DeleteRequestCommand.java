package ua.testing.repairagency.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.service.RepairRequestService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteRequestCommand implements Command {
    private Logger logger = LogManager.getLogger();
    private RepairRequestService repairRequestService = new RepairRequestService();
    private RepairRequestDto requestDto = new RepairRequestDto();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("redirect", true);
        logger.info("current request id: " + request.getParameter("id"));

        requestDto.setId(Long.parseLong(request.getParameter("id")));

        try {
            repairRequestService.deleteUserRequest(requestDto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/app/admin";
    }
}
