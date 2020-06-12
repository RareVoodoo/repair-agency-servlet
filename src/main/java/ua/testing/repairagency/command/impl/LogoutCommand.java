package ua.testing.repairagency.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.util.Constants;

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
            logger.info("Logged out");
        }
        request.setAttribute(Constants.REDIRECT_ATTRIBUTE, true);

        return Constants.LOGIN_REDIRECT;
    }

}
