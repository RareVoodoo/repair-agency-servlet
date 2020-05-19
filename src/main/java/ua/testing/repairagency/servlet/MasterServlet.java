package ua.testing.repairagency.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MasterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("role").equals("Master")){
            request.getRequestDispatcher("/WEB-INF/view/Master.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("/WEB-INF/view/error/IllegalAccessError.jsp").forward(request, response);
        }
    }
}