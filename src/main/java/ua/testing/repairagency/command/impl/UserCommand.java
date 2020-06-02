package ua.testing.repairagency.command.impl;

import ua.testing.repairagency.command.Command;
import ua.testing.repairagency.dao.impl.CommentDao;
import ua.testing.repairagency.dao.impl.RepairRequestDao;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.util.DbConnector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RepairRequestDao requestDao = new RepairRequestDao(DbConnector.getInstance().getConnection());
        CommentDao commentDao = new CommentDao(DbConnector.getInstance().getConnection());


        if (session.getAttribute("role").equals("User")) {
            try {
                String username = (String) session.getAttribute("currentUsername");
                request.setAttribute("request", requestDao.getAllByUserId(username));
                request.setAttribute("comment", commentDao.getAllById());
            } catch (PersistException e) {
                e.printStackTrace();
            }
            return "/WEB-INF/view/User.jsp";
        } else {
            return "/WEB-INF/view/error/IllegalAccessError.jsp";
        }
    }
}
