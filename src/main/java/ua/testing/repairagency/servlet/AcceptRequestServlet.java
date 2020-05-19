package ua.testing.repairagency.servlet;

import ua.testing.repairagency.dao.RepairRequestDao;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.RepairRequest;
import ua.testing.repairagency.util.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcceptRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RepairRequestDao repairDao = new RepairRequestDao(DBConnector.getConnection());
        System.out.println(request.getParameter("id"));
        long requestId = Long.parseLong(request.getParameter("requestId"));
        long price = Long.parseLong(request.getParameter("price"));

        try {
            RepairRequest repairRequest = repairDao.getById(requestId).get();
            System.out.println(repairRequest.getId());
            repairRequest.setAccepted(true);
            repairRequest.setUsdPrice(price);
            repairDao.update(repairRequest);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/admin");
    }
}
