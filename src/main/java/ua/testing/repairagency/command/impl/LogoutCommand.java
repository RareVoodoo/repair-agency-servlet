package ua.testing.repairagency.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.testing.repairagency.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session!=null)
        {
            session.invalidate();
            request.setAttribute("errMessage", "You have logged out successfully");
            logger.info("Logged out");
        }
        request.setAttribute("redirect", true);

        return "/app/login";
    }

}
