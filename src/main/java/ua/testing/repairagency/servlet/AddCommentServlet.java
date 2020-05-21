package ua.testing.repairagency.servlet;

import ua.testing.repairagency.dao.CommentDao;
import ua.testing.repairagency.dao.RepairRequestDao;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.Comment;
import ua.testing.repairagency.model.RepairRequest;
import ua.testing.repairagency.util.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CommentDao commentDao = new CommentDao(DBConnector.getConnection());
        String userComment = request.getParameter("comment");
        long repairRequestId = Long.parseLong(request.getParameter("requestId"));

        try{
            Comment comment = new Comment.Builder()
                    .comment(userComment)
                    .repairRequestId(repairRequestId)
                    .build();
            commentDao.create(comment);
        }catch (PersistException e){
            e.printStackTrace();
        }
    }
}
