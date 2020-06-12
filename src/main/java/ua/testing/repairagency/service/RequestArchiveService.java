package ua.testing.repairagency.service;

import ua.testing.repairagency.dao.impl.RepairRequestDao;
import ua.testing.repairagency.dao.impl.RequestArchiveDao;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.RepairRequest;
import ua.testing.repairagency.model.RepairRequestArchive;
import ua.testing.repairagency.util.DbConnector;

import java.sql.Connection;

public class RequestArchiveService {
    private Connection connection = DbConnector.getInstance().getConnection();
    private RequestArchiveDao archiveDao = new RequestArchiveDao(connection);
    private RepairRequestDao requestDao = new RepairRequestDao(connection);

    /**
     * Called in case of repair request deleting
     * @param repairRequest repair request object
     *
     */
    public void addRepairRequestToArchive(RepairRequest repairRequest) throws PersistException {
        try {
            archiveDao.create(new RepairRequestArchive.Builder()
                    .description(repairRequest.getDescription())
                    .accepted(repairRequest.isAccepted())
                    .performed(repairRequest.isPerformed())
                    .userPhoneNumber(repairRequest.getUserPhoneNumber())
                    .address(repairRequest.getAddress())
                    .cancellationReason(repairRequest.getCancellationReason())
                    .uahPrice(repairRequest.getUahPrice())
                    .usdPrice(repairRequest.getUsdPrice())
                    .userComment(requestDao.getRequestCommentById(repairRequest.getId()))
                    .build());
        }catch (Exception e){
            throw new PersistException(e);
        }
    }
}
