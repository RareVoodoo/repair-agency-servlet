package ua.testing.repairagency.dao;

import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class UserDao extends AbstractDao<User, Long>{

    private Connection  connection ;
    public UserDao(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    private class PersistUser extends User {
        public void setId(Long id) {
            super.setUserId(id);
        }
    }

    @Override
    public String getAllSelectQuery() {
        return "select * from user " +
                "left join authority " +
                "on id_authority = idauthority;";
    }

    @Override
    public String getSelectQuery() {
        return "select * from user " +
                "left join authority " +
                "on id_authority = idauthority " +
                "where user.iduser = ?;";
    }

    @Override
    public String getUpdateQuery() {
        return "update user set ua_name = ?" +
                ",en_name = ?," +
                "username =?," +
                "password = ?," +
                "enabled = ?," +
                "id_authority = ? "+
                "where iduser = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from user where iduser = ?;";
    }

    @Override
    public String getInsertQuery() {
        return "insert into user (ua_name, en_name, username, password, enabled, id_authority)" +
                "values(?,?,?,?,?,?);";
    }


    public String getIdByUsernameQuery(){
        return " select iduser from user\n" +
                " where username = ?;";
    }


    @Override
    public void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
        try{
            statement.setString(1, object.getUa_name());
            statement.setString(2, object.getEn_name());
            statement.setString(3, object.getUsername());
            statement.setString(4, object.getPassword());
            statement.setBoolean(5, object.isEnabled());
            statement.setInt(6, object.getIdAuthority());
        }catch (Exception e){
            throw new PersistException(e);
        }
    }

    @Override
    public List<User> parseResultSet(ResultSet resultSet) throws PersistException {
        LinkedList<User> result = new LinkedList<>();
        try {
            while (resultSet.next()){
                PersistUser user = new PersistUser();
                user.setId(resultSet.getLong("iduser"));
                user.setEn_name(resultSet.getString("en_name"));
                user.setUa_name(resultSet.getString("ua_name"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEnabled(resultSet.getBoolean("enabled"));
                user.setIdAuthority(resultSet.getInt("id_authority"));
                result.add(user);
            }
        }catch (Exception e){
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
        try{
            statement.setString(1, object.getUa_name());
            statement.setString(2, object.getEn_name());
            statement.setString(3, object.getUsername());
            statement.setString(4, object.getPassword());
            statement.setBoolean(5, object.isEnabled());
            statement.setInt(6, object.getIdAuthority());
            statement.setLong(7, object.getUserId());
        }catch (Exception e){
            throw new PersistException(e);
        }
    }

    public Long getUserIdByUsername(String username) throws PersistException {
        long userId =0;
        try (PreparedStatement statement = connection.prepareStatement(getIdByUsernameQuery())) {
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                userId = resultSet.getLong("iduser");
            }

        } catch (Exception e) {
            throw new PersistException(e);
        }
        return userId;
    }

}
