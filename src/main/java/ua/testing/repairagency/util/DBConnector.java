package ua.testing.repairagency.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnector {
    final static Logger logger = LogManager.getLogger();
    private volatile static DbConnector instance;

    private DbConnector(){
    }


    public static DbConnector getInstance(){
        if(instance == null){
            synchronized (DbConnector.class){
                if(instance == null){
                    instance = new DbConnector();
                }
            }
        }
        return instance;
    }


    public  Connection getConnection(){
        Context ctx;
        Connection connection = null;

        try{
            ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup(Constants.POOL_CONNECTION_CONTEXT_PATH);
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("SQL exception");
            e.printStackTrace();
        } catch (NamingException e) {
            logger.error("Context exception");
            e.printStackTrace();

        }
        return connection;
    }

}

