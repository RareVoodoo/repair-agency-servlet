package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.PropertyReader;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return Constants.REGISTRATION_PAGE_PATH;
    }
}
