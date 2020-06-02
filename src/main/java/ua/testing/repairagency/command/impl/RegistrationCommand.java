package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/Register.jsp";
    }
}
