package ua.testing.repairagency.servlet;

import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.service.RepairRequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RepairRequestDto repairRequestDto = new RepairRequestDto();
        HttpSession session = request.getSession();
        RepairRequestService requestService = new RepairRequestService();

        String description = request.getParameter("description");
        String userPhoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");


        repairRequestDto.setDescription(description);
        repairRequestDto.setUserPhoneNumber(userPhoneNumber);
        repairRequestDto.setAddress(address);
        repairRequestDto.setUsername((String) session.getAttribute("currentUsername"));


        try {
            requestService.createNewRepairRequest(repairRequestDto);
        } catch (PersistException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/user");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
