package ua.testing.repairagency.service;

import ua.testing.repairagency.dao.RepairRequestDao;
import ua.testing.repairagency.dao.UserDao;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.RepairRequest;
import ua.testing.repairagency.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public class RepairRequestService {
    public static final long REPAIR_PRICE_DEFAULT_VALUE = 0L;
    public static final String CANCELLATION_REASON_DEFAULT_VALUE = "not canceled";


    private Connection connection = DBConnector.getConnection();
    private RepairRequestDao repairDao = new RepairRequestDao(connection);
    private UserDao userDao = new UserDao(connection);

    public void createNewRepairRequest(RepairRequestDto requestDto) throws PersistException {


        try {
            repairDao.create(new RepairRequest.Builder()
                    .userId(userDao.getUserIdByUsername(requestDto.getUsername()))
                    .description(requestDto.getDescription())
                    .accepted(false)
                    .performed(false)
                    .userPhoneNumber(requestDto.getUserPhoneNumber())
                    .address(requestDto.getAddress())
                    .cancellationReason(CANCELLATION_REASON_DEFAULT_VALUE)
                    .uahPrice(REPAIR_PRICE_DEFAULT_VALUE)
                    .usdPrice(REPAIR_PRICE_DEFAULT_VALUE)
                    .build());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public HashMap<String, Object> releaseRepairRequestDatasetMap() throws PersistException {
        HashMap<String, Object> map = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(repairDao.getAllSelectQuery())) {

            ResultSet resultSet = statement.executeQuery();
            List<RepairRequest> repairRequests = repairDao.parseResultSet(resultSet);

            for (RepairRequest request : repairRequests){
                map.put("id",request.getId());
                map.put("description", request.getDescription());
                map.put("accepted",request.isAccepted());
                map.put("performed", request.isPerformed());
                map.put("cancellationReason", request.getCancellationReason());
                map.put("uahPrice", request.getUahPrice());
                map.put("usdPrice", request.getUsdPrice());
            }
        }catch (Exception e){
            throw new PersistException(e);
        }
        return map;
    }
}
