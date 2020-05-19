package ua.testing.repairagency.dao;

import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.Comment;
import ua.testing.repairagency.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class CommentDao extends AbstractDao<Comment, Long> {

    public CommentDao(Connection connection) {
        super(connection);
    }


    private class PersistComment extends Comment {
        public void setId(Long id) {
            super.setId(id);
        }
    }


    @Override
    public String getAllSelectQuery() {
        return "SELECT * FROM comment " +
                "inner join repair_request " +
                "on comment.id_repair_request = repair_request.idrepair_request;";
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM comment\n" +
                "where id_user_comment =  ?;";
    }

    @Override
    public String getUpdateQuery() {
        return "update comment set comment =?, id_repair_request = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from comment where id_user_comment = ?";
    }

    @Override
    public String getInsertQuery() {
        return "insert into comment(comment, id_repair_request) " +
                "values (?,?)";
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement statement, Comment object) throws PersistException {
        try {
            statement.setString(1, object.getComment());
            statement.setLong(2, object.getRepairRequestId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<Comment> parseResultSet(ResultSet resultSet) throws PersistException {
        LinkedList<Comment> result = new LinkedList<>();
        try {
            while (resultSet.next()) {
                PersistComment comment = new PersistComment();
                comment.setId(resultSet.getLong("id_user_comment"));
                comment.setComment(resultSet.getString("comment"));
                comment.setRepairRequestId(resultSet.getLong("id_repair_request"));
                result.add(comment);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Comment object) throws PersistException {
        try{
            statement.setString(1,object.getComment());
            statement.setLong(2, object.getRepairRequestId());
        }catch (Exception e){
            throw new PersistException(e);
        }
    }
}
