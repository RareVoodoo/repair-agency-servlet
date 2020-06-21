package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.CommentDto;
import ua.testing.repairagency.dto.UserDto;
import ua.testing.repairagency.model.Comment;
import ua.testing.repairagency.service.UserCommentService;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.FormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserCommentCommand implements Command {
    private UserCommentService userCommentService = new UserCommentService();
    private CommentDto commentDto = new CommentDto();
    private FormValidator<CommentDto> formValidator = new FormValidator<>();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        request.setAttribute(Constants.REDIRECT_ATTRIBUTE, true);

        commentDto.setComment(request.getParameter(Constants.COMMENT_ATTRIBUTE));
        commentDto.setRepairRequestId(Long.parseLong(request.getParameter(Constants.REQUEST_ID_PARAM)));


        if(formValidator.isValidFields(commentDto)){
            userCommentService.createUserComment(commentDto);
        }

        session.setAttribute(Constants.ERRORS_ATTRIBUTE, formValidator.getErrors());

        return Constants.USER_REDIRECT;
    }
}
