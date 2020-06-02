package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.service.RepairRequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateRequestCommand implements Command {
    private RepairRequestService requestService = new RepairRequestService();
    private RepairRequestDto requestDto = new RepairRequestDto();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String description = request.getParameter("description");
        String userPhoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");


        requestDto.setDescription(description);
        requestDto.setUserPhoneNumber(userPhoneNumber);
        requestDto.setAddress(address);
        requestDto.setUsername((String) session.getAttribute("currentUsername"));

        try {
            requestService.createNewRepairRequest(requestDto);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return "/app/user";
    }
}
