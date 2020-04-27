package ua.testing.repairagency.servlet;

import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(userName);
        userDTO.setPassword(password);

        LoginService loginService = new LoginService();

        try
        {
            String userValidate = loginService.authenticateUser(userDTO);

            switch (userValidate) {
                case "Admin_Role": {
                    System.out.println("Admin's Home");

                    HttpSession session = request.getSession(); //Creating a session

                    session.setAttribute("Admin", userName); //setting session attribute

                    request.setAttribute("userName", userName);

                    request.getRequestDispatcher("/WEB-INF/view/Admin.jsp").forward(request, response);
//                response.sendRedirect(request.getContextPath() + "/admin");
                    break;
                }
                case "Editor_Role": {
                    System.out.println("Editor's Home");

                    HttpSession session = request.getSession();
                    session.setAttribute("Editor", userName);
                    request.setAttribute("userName", userName);

                    request.getRequestDispatcher("/WEB-INF/view/Editor.jsp").forward(request, response);
                    break;
                }
                case "User_Role": {
                    System.out.println("User's Home");

                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(10 * 60);
                    session.setAttribute("User", userName);
                    request.setAttribute("userName", userName);

                    request.getRequestDispatcher("/WEB-INF/view/User.jsp").forward(request, response);
                    break;
                }
                default:
                    System.out.println("Error message = " + userValidate);
                    request.setAttribute("errMessage", userValidate);
                    System.out.println("else block");

                    request.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);
                    break;
            }
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(request, response);

    }
}
