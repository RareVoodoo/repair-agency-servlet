package ua.testing.repairagency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.testing.repairagency.command.*;
import ua.testing.repairagency.command.impl.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private Logger logger = LogManager.getLogger();

    public void init(ServletConfig servletConfig) {

        servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<String>());


        commands.put("login", new LoginCommand());
        commands.put("user", new UserCommand());
        commands.put("authentication", new AuthenticateCommand());
        commands.put("master", new MasterCommand());
        commands.put("admin", new AdminCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("registerUser", new RegisterUserCommand());
        commands.put("registration", new RegistrationCommand());

        commands.put("user/createRequest", new CreateRequestCommand());
        commands.put("user/commentRequest",new UserCommentCommand());

        commands.put("admin/deleteRequest", new DeleteRequestCommand());
        commands.put("admin/acceptRequest", new AcceptRequestCommand());
        commands.put("admin/cancelRequest", new CancelRequestCommand());

        commands.put("master/performRequest", new PerformRequestCommand());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String path = request.getRequestURI();
        path = path.replaceAll(".*/app/", "");
        Command command = commands.getOrDefault(path,
                (r) -> "/WEB-INF/view/Login.jsp");
        logger.info(command.getClass().getName());
        String page = command.execute(request);

        if (isRequiresRedirect(request)) {
            request.setAttribute("redirect", false);
            response.sendRedirect(page);
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    private boolean isRequiresRedirect(HttpServletRequest request) {
        logger.info("redirect = " + request.getAttribute("redirect"));
        return (boolean) request.getAttribute("redirect");
    }

}
