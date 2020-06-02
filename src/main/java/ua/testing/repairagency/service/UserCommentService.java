package ua.testing.repairagency.service;

import ua.testing.repairagency.dao.impl.CommentDao;
import ua.testing.repairagency.dto.CommentDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.Comment;
import ua.testing.repairagency.util.DbConnector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserCommentService {
    public void createUserComment(CommentDto commentDto){
        CommentDao commentDao = new CommentDao(DbConnector.getInstance().getConnection());

        try{
            Comment comment = new Comment.Builder()
                    .comment(commentDto.getComment())
                    .repairRequestId(commentDto.getRepairRequestId())
                    .build();
            commentDao.create(comment);

        }catch (PersistException e){
            e.printStackTrace();
        }
    }
}
