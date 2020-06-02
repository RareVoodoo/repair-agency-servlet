package ua.testing.repairagency.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.testing.repairagency.dao.impl.RepairRequestDao;
import ua.testing.repairagency.dao.impl.UserDao;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.exception.PersistException;
import ua.testing.repairagency.model.RepairRequest;
import ua.testing.repairagency.region.currency.CurrencyConversion;
import ua.testing.repairagency.region.transliteration.NameTransliteration;
import ua.testing.repairagency.util.DbConnector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

public class RepairRequestService {
    private Logger logger = LogManager.getLogger();

    private static final long REPAIR_PRICE_DEFAULT = 0L;
    private static final String CANCELLATION_REASON_DEFAULT = "not canceled";
    private static final int NUMBER_OF_PAGES_DEFAULT = 3;
    private static final int CURRENT_PAGE_DEFAULT = 1;


    private Connection connection = DbConnector.getInstance().getConnection();
    private RepairRequestDao repairDao = new RepairRequestDao(connection);
    private UserDao userDao = new UserDao(connection);
    private RepairRequestDao requestDao = new RepairRequestDao(connection);
    private RequestArchiveService archiveService = new RequestArchiveService();




    public void createNewRepairRequest(RepairRequestDto requestDto) throws PersistException {

        try {
            repairDao.create(new RepairRequest.Builder()
                    .userId(userDao.getUserIdByUsername(requestDto.getUsername()))
                    .description(requestDto.getDescription())
                    .accepted(false)
                    .performed(false)
                    .userPhoneNumber(requestDto.getUserPhoneNumber())
                    .address(requestDto.getAddress())
                    .cancellationReason(CANCELLATION_REASON_DEFAULT)
                    .uahPrice(REPAIR_PRICE_DEFAULT)
                    .usdPrice(REPAIR_PRICE_DEFAULT)
                    .build());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public void paginatePage(HttpServletRequest request) {
        int currentPage;
        int recordsPerPage = NUMBER_OF_PAGES_DEFAULT;

        if (request.getParameter("currentPage") == null) {
            currentPage = CURRENT_PAGE_DEFAULT;
        } else {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

            try {
                request.setAttribute("request", requestDao.getAllByLimit(currentPage, recordsPerPage));
                int rows = requestDao.getNumberOfRows();
                int numberOfPages = (int) Math.ceil(rows * 1.0 / recordsPerPage);


                request.setAttribute("noOfPages", numberOfPages);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("recordsPerPage", recordsPerPage);


            }catch (PersistException e) {
                e.printStackTrace();
            }
    }


    public void acceptUserRequest(RepairRequestDto  repairRequestDto, HttpSession session){

        CurrencyConversion currencyConversion =
                new CurrencyConversion((Locale) session.getAttribute("currentLocale"));

        long requestId = repairRequestDto.getId();
        long price = repairRequestDto.getUsdPrice();

        try {
            RepairRequest repairRequest = repairDao.getById(requestId).get();
            repairRequest.setAccepted(true);
            repairRequest.setUsdPrice(currencyConversion.convert(NameTransliteration.EN_LOCALE, price));
            repairRequest.setUahPrice(currencyConversion.convert(NameTransliteration.UA_LOCALE, price));
            repairDao.update(repairRequest);
            logger.info("Description: " + repairRequest.getDescription());

        } catch (PersistException e) {
            e.printStackTrace();
        }

    }

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
