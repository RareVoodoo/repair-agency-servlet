package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.service.login.LoginService;
import ua.testing.repairagency.util.Constants;

import javax.servlet.http.HttpServletRequest;

public class AuthenticateCommand implements Command {
    private LoginService loginService = new LoginService();
    private UserDto userDto = new UserDto();
    @Override
    public String execute(HttpServletRequest request) {
        String userName = request.getParameter(Constants.USERNAME_PARAM);
        String password = request.getParameter(Constants.PASSWORD_PARAM);

        userDto.setUsername(userName);
        userDto.setPassword(password);

        return loginService.authenticateUser(userDto,request);
    }
}
