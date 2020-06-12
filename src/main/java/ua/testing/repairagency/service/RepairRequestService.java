package ua.testing.repairagency.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.testing.repairagency.dao.impl.RepairRequestDao;
import ua.testing.repairagency.dao.impl.UserDao;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.RepairRequest;
import ua.testing.repairagency.region.currency.CurrencyConversion;
import ua.testing.repairagency.util.Constants;
import ua.testing.repairagency.util.DbConnector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

public class RepairRequestService {
    private Logger logger = LogManager.getLogger();


    private Connection connection = DbConnector.getInstance().getConnection();
    private RepairRequestDao repairDao = new RepairRequestDao(connection);
    private UserDao userDao = new UserDao(connection);
    private RepairRequestDao requestDao = new RepairRequestDao(connection);
    private RequestArchiveService archiveService = new RequestArchiveService();


    /**
     * Add new repair request to database
     * @param requestDto repair request object
     *
     */
    public void createNewRepairRequest(RepairRequestDto requestDto) throws PersistException {
        try {
            repairDao.create(new RepairRequest.Builder()
                    .userId(userDao.getUserIdByUsername(requestDto.getUsername()))
                    .description(requestDto.getDescription())
                    .accepted(false)
                    .performed(false)
                    .userPhoneNumber(requestDto.getUserPhoneNumber())
                    .address(requestDto.getAddress())
                    .cancellationReason(Constants.CANCELLATION_REASON_DEFAULT)
                    .uahPrice(Constants.REPAIR_PRICE_DEFAULT)
                    .usdPrice(Constants.REPAIR_PRICE_DEFAULT)
                    .build());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }


    /**
     * Returns already paginated page
     * @param request servlet request
     */
    public void paginatePage(HttpServletRequest request) {
        int currentPage;
        int recordsPerPage = Constants.NUMBER_OF_PAGES_DEFAULT;

        if (request.getParameter(Constants.CURRENT_PAGE_PARAM) == null) {
            currentPage = Constants.CURRENT_PAGE_DEFAULT;
        } else {
            currentPage = Integer.parseInt(request.getParameter(Constants.CURRENT_PAGE_PARAM));
        }

            try {
                request.setAttribute(Constants.REQUEST_ATTRIBUTE, requestDao.getAllByLimit(currentPage, recordsPerPage));
                int rows = requestDao.getNumberOfRows();
                int numberOfPages = (int) Math.ceil(rows * 1.0 / recordsPerPage);

                request.setAttribute(Constants.NUMBER_OF_PAGES_ATTRIBUTE, numberOfPages);
                request.setAttribute(Constants.CURRENT_PAGE_ATTRIBUTE, currentPage);
                request.setAttribute(Constants.RECORDS_PER_PAGE_ATTRIBUTE, recordsPerPage);

            }catch (PersistException e) {
                e.printStackTrace();
            }
    }

    /**
     * Accepts repair request and sets status to "accepted"
     * @param repairRequestDto repair request object
     * @param session current Http Session
     */
    public void acceptUserRequest(RepairRequestDto  repairRequestDto, HttpSession session){
        Locale currentLocale = (Locale) session.getAttribute(Constants.CURRENT_LOCALE_ATTRIBUTE);
        CurrencyConversion currencyConversion = new CurrencyConversion(currentLocale);

        long requestId = repairRequestDto.getId();
        long price = repairRequestDto.getUsdPrice();

        try {
            RepairRequest repairRequest = repairDao.getById(requestId).get();
            repairRequest.setAccepted(true);
            repairRequest.setUsdPrice(currencyConversion.convert(Constants.EN_LOCALE, price));
            repairRequest.setUahPrice(currencyConversion.convert(Constants.UA_LOCALE, price));
            repairDao.update(repairRequest);
            logger.info("Description: " + repairRequest.getDescription());

        } catch (PersistException e) {
            e.printStackTrace();
        }

    }

    /**
     * Cancels repair request and sets status to "unperformed"
     * @param requestDto repair request object
     */
    public void cancelUserRequest(RepairRequestDto requestDto){

        try {
            RepairRequest repairRequest = repairDao.getById(requestDto.getId()).get();
            logger.info(repairRequest.getId());
            repairRequest.setAccepted(false);
            repairRequest.setCancellationReason(requestDto.getCancellationReason());
            repairDao.update(repairRequest);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }



    /**
     * Removes user from database and adds him to repair request archive
     * @param requestDto repair request object
     * @throws SQLException
     */
    public void deleteUserRequest(RepairRequestDto requestDto) throws SQLException {

        try {
            Optional<RepairRequest> repairRequest = repairDao.getById(requestDto.getId());

            connection.setAutoCommit(false);
            archiveService.addRepairRequestToArchive(repairRequest.get());
            repairDao.delete(repairRequest.get());
            connection.commit();
            connection.setAutoCommit(true);

        } catch (PersistException | SQLException e) {
            e.printStackTrace();
            logger.info("Request archiving /deletion error");
            connection.rollback();
        }
    }


    /**
     * Sets repair request status to "performed"
     * @param requestDto repair request object
     */
    public void performUserRequest(RepairRequestDto requestDto){
        RepairRequestDao repairDao = new RepairRequestDao(connection);

        try {
            RepairRequest repairRequest = repairDao.getById(requestDto.getId()).get();
            logger.info("Request id is -" + repairRequest.getId());
            repairRequest.setPerformed(true);
            repairDao.update(repairRequest);
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }


}
