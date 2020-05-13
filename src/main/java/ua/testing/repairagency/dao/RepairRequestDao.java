package ua.testing.repairagency.dao;

import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.RepairRequest;
import ua.testing.repairagency.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class RepairRequestDao extends AbstractDao<RepairRequest, Long> {

    public RepairRequestDao(Connection connection) {
        super(connection);
    }

    private class PersistRepairRequest extends RepairRequest {
        public void setId(Long id) {
            super.setId(id);
        }
    }

    @Override
    public String getAllSelectQuery() {
        return "select * from repair_request";
    }

    @Override
    public String getSelectQuery() {
        return "select * from repair_request " +
                "where idrepair_request = ?";
    }

    @Override
    public String getUpdateQuery() {
        return " update repair_request set description = ?, accepted =?,\n" +
                " performed = ?,\n" +
                " cancellation_reason = ?,\n" +
                " uah_price = ?,\n" +
                " usd_price = ?,\n" +
                " user_iduser = ?" +
                "where idrepair_request = ?";
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
            statement.setLong(4, object.getUahPrice());
            statement.setLong(5, object.getUsdPrice());
            statement.setLong(6, object.getUserId());
            statement.setLong(7, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
