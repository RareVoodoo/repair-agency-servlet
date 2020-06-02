package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dto.CommentDto;
import ua.testing.repairagency.model.Comment;
import ua.testing.repairagency.service.UserCommentService;

import javax.servlet.http.HttpServletRequest;

public class UserCommentCommand implements Command {
    private UserCommentService userCommentService = new UserCommentService();
    private CommentDto commentDto = new CommentDto();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("redirect", true);

        commentDto.setComment(request.getParameter("comment"));
        commentDto.setRepairRequestId(Long.parseLong(request.getParameter("requestId")));

        userCommentService.createUserComment(commentDto);
        return "/app/user";
    }
}
