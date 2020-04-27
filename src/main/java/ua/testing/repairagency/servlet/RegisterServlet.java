package ua.testing.repairagency.servlet;

import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.service.LoginService;
import ua.testing.repairagency.service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserDTO userDTO = new UserDTO();
        RegisterService registerService = new RegisterService();

        userDTO.setUsername(userName);
        userDTO.setPassword(password);
        userDTO.setEmail(email);

        registerService.createNewUser(userDTO);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/Register.jsp").forward(request, response);
    }
}
