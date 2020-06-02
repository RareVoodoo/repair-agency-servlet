package ua.testing.repairagency.dao.impl;

import ua.testing.repairagency.dao.AbstractDao;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.RepairRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class RepairRequestDao extends AbstractDao<RepairRequest, Long> {
    private Connection connection;

    public RepairRequestDao(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    private class PersistRepairRequest extends RepairRequest {
        public void setId(Long id) {
            super.setId(id);
        }
    }

    @Override
    public String getAllSelectQuery() {
        return "select * from repair_request;";
    }


    public String getAllSelectWithLimitQuery() {
        return "select * from repair_request limit ?,?;";
    }

    public String getAllByUsernameQuery() {
        return "select repair_request.* from repair_request \n" +
                "inner join user\n" +
                "on iduser = user_iduser\n" +
                "where username = ?\n" +
                "and accepted & performed\n";
    }

    public String getAllAcceptedQuery() {
        return "select * from repair_request \n" +
                "where accepted";
    }

    @Override
    public String getSelectQuery() {
        return "select * from repair_request " +
                "where idrepair_request = ?";
    }

    @Override
    public String getUpdateQuery() {
        return " update repair_request set \n" +
                "`description` = ?,\n" +
                "accepted =?,\n" +
                "performed = ?,\n" +
                "cancellation_reason = ?,\n" +
                "uah_price = ?,\n" +
                "usd_price = ?,\n" +
                "user_iduser = ?,\n" +
                "address = ?,\n" +
                "user_phone_number = ? \n" +
                "where idrepair_request = ?;";
    }

    public String getRequestCommentQuery() {
        return "select `comment` from ( select idrepair_request, `comment` FROM repair_request\n" +
                "inner join `comment`\n" +
                "where id_repair_request = idrepair_request) as comment_query\n" +
                "where idrepair_request = ?;";
    }


    @Override
    public String getDeleteQuery() {
        return "delete from repair_request where idrepair_request = ?";
    }

    @Override
    public String getInsertQuery() {
        return "insert into repair_request " +
                "(description, accepted, performed, uah_price, usd_price,address, user_phone_number,user_iduser)" +
                " values (?,?,?,?,?,?,?,?)";
    }

    public String getNumberOfRowsQuery() {
        return "select count(idrepair_request) as columns_count from repair_request";
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement statement, RepairRequest object) throws PersistException {
        try {
            statement.setString(1, object.getDescription());
            statement.setBoolean(2, object.isAccepted());
            statement.setBoolean(3, object.isPerformed());
            statement.setLong(4, object.getUahPrice());
            statement.setLong(5, object.getUsdPrice());
            statement.setString(6, object.getAddress());
            statement.setString(7, object.getUserPhoneNumber());
            statement.setString(7, object.getUserPhoneNumber());
            statement.setLong(8, object.getUserId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<RepairRequest> parseResultSet(ResultSet resultSet) throws PersistException {
        LinkedList<RepairRequest> result = new LinkedList<>();

        try {
            while (resultSet.next()) {
                PersistRepairRequest repairRequest = new PersistRepairRequest();
                repairRequest.setId(resultSet.getLong("idrepair_request"));
                repairRequest.setDescription(resultSet.getString("description"));
                repairRequest.setAccepted(resultSet.getBoolean("accepted"));
                repairRequest.setPerformed(resultSet.getBoolean("performed"));
                repairRequest.setCancellationReason(resultSet.getString("cancellation_reason"));
                repairRequest.setUahPrice(resultSet.getLong("uah_price"));
                repairRequest.setUsdPrice(resultSet.getLong("usd_price"));
                repairRequest.setAddress(resultSet.getString("address"));
                repairRequest.setUserPhoneNumber(resultSet.getString("user_phone_number"));
                repairRequest.setUserId(resultSet.getLong("user_iduser"));
                result.add(repairRequest);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, RepairRequest object) throws PersistException {
        try {
            statement.setString(1, object.getDescription());
            statement.setBoolean(2, object.isAccepted());
            statement.setBoolean(3, object.isPerformed());
            statement.setString(4, object.getCancellationReason());
            statement.setLong(5, object.getUahPrice());
            statement.setLong(6, object.getUsdPrice());
            statement.setLong(7, object.getUserId());
            statement.setString(8, object.getAddress());
            statement.setString(9, object.getUserPhoneNumber());
            statement.setLong(10, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public List<RepairRequest> getAllByUserId(String username) throws PersistException {
        List<RepairRequest> list;

        try (PreparedStatement statement = connection.prepareStatement(getAllByUsernameQuery())) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException();
        }
        return list;
    }

    public List<RepairRequest> getAllAcceptedRequests() throws PersistException {
        List<RepairRequest> list;
        try (PreparedStatement statement = connection.prepareStatement(getAllAcceptedQuery())) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException();
        }
        return list;
    }

    public String getRequestCommentById(Long id) throws PersistException {
        String comment = "";
        try (PreparedStatement statement = connection.prepareStatement(getRequestCommentQuery())) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                comment = resultSet.getString("comment");
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return comment;
    }


    public int getNumberOfRows() throws PersistException {
        int numOfRows = 0;
        try (PreparedStatement statement = connection.prepareStatement(getNumberOfRowsQuery())) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                numOfRows = resultSet.getInt("columns_count");
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return numOfRows;
    }

    public List<RepairRequest> getAllByLimit(int currentPage, int recordsPerPage) throws PersistException {
        List<RepairRequest> list;
        int start = currentPage * recordsPerPage - recordsPerPage;
        try (PreparedStatement statement = connection.prepareStatement(getAllSelectWithLimitQuery())) {
            statement.setInt(1, start);
            statement.setInt(2, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        } catch (Exception e) {
            throw new PersistException();
        }
        return list;
    }
}
