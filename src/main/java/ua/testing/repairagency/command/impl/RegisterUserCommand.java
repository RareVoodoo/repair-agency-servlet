package ua.testing.repairagency.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.taglibs.standard.lang.jstl.GreaterThanOperator;
import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.service.RegisterService;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.PasswordEncryptor;

import javax.servlet.http.HttpServletRequest;

public class  RegisterUserCommand implements Command {
    private UserDto userDto = new UserDto();
    private RegisterService registerService = new RegisterService();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(Constants.REDIRECT_ATTRIBUTE, true);

        userDto.setUsername(request.getParameter(Constants.USERNAME_PARAM));
        userDto.setPassword(request.getParameter(Constants.PASSWORD_PARAM));
        userDto.setEn_name(request.getParameter(Constants.FULL_NAME_PARAM));

        registerService.registerUser(userDto);


        return Constants.LOGIN_REDIRECT;
    }
}
