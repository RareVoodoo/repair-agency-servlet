package ua.testing.repairagency.dao;

import ua.testing.repairagency.exception.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends  Identified<PK>, PK extends Long> implements GenericDao<T> {

    private Connection connection;

    public abstract String getAllSelectQuery();
    public abstract String getSelectQuery();
    public abstract String getUpdateQuery();
    public abstract String getDeleteQuery();
    public abstract String getInsertQuery();


    public abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    public abstract List<T> parseResultSet(ResultSet resultSet) throws PersistException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

    @Override
    public Optional<T> getById(long id) throws PersistException {
        List<T> list;
        String sqlQuery = getSelectQuery();
        try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setLong(1,id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);

        }catch (Exception e){
            throw new PersistException();
        }

        if(list == null|| list.size() == 0){
            throw new PersistException("Record with PK = " + id + " not found");
        }
        if(list.size() >1){
            throw new PersistException("Received more than one record");

        }

        return Optional.ofNullable(list.iterator().next());
    }

    @Override
    public List<T> getAllById() throws PersistException {
        List<T> list;
        String sqlQuery = getAllSelectQuery();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException();
        }
        return list;
    }


    @Override
    public void update(T object) throws PersistException {
        String sql = getUpdateQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(preparedStatement, object);
           preparedStatement.setObject(1, object.getId());
           int count = preparedStatement.executeUpdate();
           if(count != 1){
               throw new PersistException("On update modify more than 1 record: " + count );
           }
        }catch (Exception e){
            throw new PersistException(e);
        }
    }

    @Override
    public void delete(T object) throws PersistException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public void create(T object) throws PersistException {
        String sql = getInsertQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }
}
