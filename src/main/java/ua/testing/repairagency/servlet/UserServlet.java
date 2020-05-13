package ua.testing.repairagency.servlet;

import ua.testing.repairagency.dao.RepairRequestDao;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.util.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RepairRequestService repairRequestService = new RepairRequestService();
        RepairRequestDao requestDao = new RepairRequestDao(DBConnector.getConnection());


        if(session.getAttribute("role").equals("User")){
            try {
                request.setAttribute("request", requestDao.getAll());
//                request.setAttribute("request", repairRequestService.releaseRepairRequestDatasetMap());
            } catch (PersistException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/WEB-INF/view/User.jsp").forward(request, response);


        }else {
            request.getRequestDispatcher("/WEB-INF/view/error/IllegalAccessError.jsp").forward(request, response);
        }
    }
}
