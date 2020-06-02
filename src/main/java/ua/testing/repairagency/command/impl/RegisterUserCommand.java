package ua.testing.repairagency.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.taglibs.standard.lang.jstl.GreaterThanOperator;
import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.service.RegisterService;
import ua.testing.repairagency.util.PasswordEncryptor;

import javax.servlet.http.HttpServletRequest;

public class RegisterUserCommand implements Command {
    private Logger logger = LogManager.getLogger();
    private UserDto userDto = new UserDto();
    private RegisterService registerService = new RegisterService();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("redirect", true);

        userDto.setUsername(request.getParameter("username"));
        userDto.setPassword(request.getParameter("password"));
        userDto.setEn_name(request.getParameter("fullName"));

        registerService.registerUser(userDto);


        return "/app/login";
    }
}
