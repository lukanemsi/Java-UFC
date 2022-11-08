package ge.ufc.webapps.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    private static final Logger logger = LogManager.getLogger();
    private static final String TOMCAT_BD = "java:comp/env/jdbc/TBCBDS";
    public static Connection getDatabaseConnection() throws SQLException, NamingException {
        logger.info("Connecting");
        return getConnection();
    }
    private static Connection getConnection() throws SQLException, NamingException{
        DataSource ds = getDataSource();
        return ds.getConnection();
    }
    private static DataSource getDataSource() throws NamingException {
        Context initCtx = new InitialContext();
        return (DataSource) initCtx.lookup(DatabaseManager.TOMCAT_BD);
    }
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.warn("Unable to close connection", e);
            }
        }
    }
}