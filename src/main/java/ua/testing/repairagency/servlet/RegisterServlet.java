package ua.testing.repairagency.servlet;

import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.service.RegisterService;
import ua.testing.repairagency.service.RepairRequestService;
import ua.testing.repairagency.util.PasswordEncryptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("fullName");




        UserDto userDTO = new UserDto();
        RegisterService registerService = new RegisterService();


        userDTO.setUsername(userName);
        userDTO.setPassword(passwordEncryptor.encrypt(password));
        userDTO.setEn_name(email);

        try {
            registerService.createNewUser(userDTO);
        } catch (PersistException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/Register.jsp").forward(request, response);
    }
}
