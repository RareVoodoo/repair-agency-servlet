package ua.testing.repairagency.dao.impl;

import ua.testing.repairagency.dao.AbstractDao;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.RepairRequestArchive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class RequestArchiveDao extends AbstractDao<RepairRequestArchive, Long> {
    private Connection connection;
    public RequestArchiveDao(Connection connection) {
        super(connection);
        this.connection = connection;
    }


    private class PersistRepairRequest extends RepairRequestArchive {
        public void setId(Long id) {
            super.setId(id);
        }
    }

    @Override
    public String getAllSelectQuery() {
        return "select * from request_archive;";
    }

    @Override
    public String getSelectQuery() {
        return "select * from request_archive \n" +
                "where id_archive = ?;";
    }

    @Override
    public String getUpdateQuery() {
        return "update request_archive set\n" +
                "description = ?,\n" +
                "accepted = ?,\n" +
                "performed = ?,\n" +
                "cancellation_reason = ?,\n" +
                "uah_price = ?,\n" +
                "usd_price = ?,\n" +
                "address = ?,\n" +
                "user_phone_number = ?,\n" +
                "user_comment = ?\n" +
                "where id_archive = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "delete from request_archive where id_archive = ?;";
    }

    @Override
    public String getInsertQuery() {
        return "insert into request_archive (description, accepted, performed, cancellation_reason," +
                " uah_price, usd_price,address, user_phone_number,user_comment)\n" +
                "values (?,?,?,?,?,?,?,?,?);";
    }


    @Override
    public void prepareStatementForInsert(PreparedStatement statement, RepairRequestArchive object) throws PersistException {
        try {
            statement.setString(1, object.getDescription());
            statement.setBoolean(2, object.isAccepted());
            statement.setBoolean(3, object.isPerformed());
            statement.setString(4, object.getCancellationReason());
            statement.setLong(5, object.getUahPrice());
            statement.setLong(6, object.getUsdPrice());
            statement.setString(7, object.getAddress());
            statement.setString(8, object.getUserPhoneNumber());
            statement.setString(9, object.getUserComment());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<RepairRequestArchive> parseResultSet(ResultSet resultSet) throws PersistException {
        LinkedList<RepairRequestArchive> result = new LinkedList<>();

        try {
            while (resultSet.next()) {
                RequestArchiveDao.PersistRepairRequest repairRequest = new PersistRepairRequest();
                repairRequest.setId(resultSet.getLong("id_archive"));
                repairRequest.setDescription(resultSet.getString("`description`"));
                repairRequest.setAccepted(resultSet.getBoolean("accepted"));
                repairRequest.setPerformed(resultSet.getBoolean("performed"));
                repairRequest.setCancellationReason(resultSet.getString("cancellation_reason"));
                repairRequest.setUahPrice(resultSet.getLong("uah_price"));
                repairRequest.setUsdPrice(resultSet.getLong("usd_price"));
                repairRequest.setAddress(resultSet.getString("address"));
                repairRequest.setUserPhoneNumber(resultSet.getString("user_phone_number"));
                repairRequest.setUserComment(resultSet.getString("user_comment"));
                result.add(repairRequest);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, RepairRequestArchive object) throws PersistException {
        try {
            statement.setString(1, object.getDescription());
            statement.setBoolean(2, object.isAccepted());
            statement.setBoolean(3, object.isPerformed());
            statement.setString(4, object.getCancellationReason());
            statement.setLong(5, object.getUahPrice());
            statement.setLong(6, object.getUsdPrice());
            statement.setString(7, object.getAddress());
            statement.setString(8, object.getUserPhoneNumber());
            statement.setString(9, object.getUserComment());
            statement.setLong(10, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

}
