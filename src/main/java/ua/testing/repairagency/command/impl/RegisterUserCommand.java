package ua.testing.repairagency.command.impl;


import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.UserDto;

import ua.testing.repairagency.service.RegisterService;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.FormValidator;


import javax.servlet.http.HttpServletRequest;

public class  RegisterUserCommand implements Command {
    private UserDto userDto = new UserDto();
    private RegisterService registerService = new RegisterService();
    private FormValidator<UserDto> formValidator = new FormValidator<>();

    @Override
    public String execute(HttpServletRequest request) {

        userDto.setUsername(request.getParameter(Constants.USERNAME_PARAM));
        userDto.setPassword(request.getParameter(Constants.PASSWORD_PARAM));
        userDto.setEn_name(request.getParameter(Constants.FULL_NAME_PARAM));

        if(formValidator.isValidFields(userDto)){
            registerService.registerUser(userDto);
        }

        request.setAttribute(Constants.ERRORS_ATTRIBUTE, formValidator.getErrors());


        return Constants.REGISTRATION_PAGE_PATH;
    }
}
