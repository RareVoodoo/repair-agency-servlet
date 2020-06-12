package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.CommentDto;
import ua.testing.repairagency.model.Comment;
import ua.testing.repairagency.service.UserCommentService;
import ua.testing.repairagency.util.Constants;

import javax.servlet.http.HttpServletRequest;

public class UserCommentCommand implements Command {
    private UserCommentService userCommentService = new UserCommentService();
    private CommentDto commentDto = new CommentDto();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(Constants.REDIRECT_ATTRIBUTE, true);

        commentDto.setComment(request.getParameter(Constants.COMMENT_ATTRIBUTE));
        commentDto.setRepairRequestId(Long.parseLong(request.getParameter(Constants.REQUEST_ID_PARAM)));

        userCommentService.createUserComment(commentDto);
        return Constants.USER_REDIRECT;
    }
}
