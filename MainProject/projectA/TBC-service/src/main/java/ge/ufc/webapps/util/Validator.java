package ge.ufc.webapps.util;
import ge.ufc.webapps.config.DatabaseManager;
import ge.ufc.webapps.faults.AgentAccessDeniedException;
import ge.ufc.webapps.faults.AgentAuthFailedException;
import ge.ufc.webapps.faults.InternalErrorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class Validator
{
    private static Connection connection;
    private static final Logger logger = LogManager.getLogger();
    private static final String userStatement = "SELECT * FROM agents WHERE id = ? AND password = ?";
    private static final String findIp = "SELECT allowed_ip FROM agent_access WHERE agent_id = ?";

    public static void validateRequest(HttpServletRequest httpServletRequest) throws AgentAuthFailedException, AgentAccessDeniedException, InternalErrorException {
        setConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(userStatement))
        {
            final int agent_id = Integer.parseInt(httpServletRequest.getHeader("agent"));
            final String password;
            try{
                password = new String(Base64.getDecoder().decode(httpServletRequest.getHeader("pass")));
            }catch (IllegalArgumentException e)
            {
                logger.error(e.getMessage());
                throw new AgentAuthFailedException(e);
            }
            preparedStatement.setInt(1, agent_id);
            preparedStatement.setString(2,password);
            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                if(resultSet.next())
                {
                    try(PreparedStatement ipStatement = connection.prepareStatement(findIp))
                    {
                        ipStatement.setInt(1,agent_id);
                        try(ResultSet ipResult = ipStatement.executeQuery())
                        {
                            if(ipResult.next())
                            {
                                String allowedIp = ipResult.getString("allowed_ip");
                                if(!allowedIp.equals(httpServletRequest.getRemoteAddr()))
                                    throw new AgentAccessDeniedException();
                            }
                            else
                                throw new AgentAccessDeniedException();

                        }
                    }
                }else
                    throw new AgentAuthFailedException();

            }
        } catch (SQLException e)
        {
            logger.error(e);
            throw new InternalErrorException(e);
        }
        finally {
            DatabaseManager.close(connection);
        }
    }
    private static void setConnection()
    {
        try {
            connection = DatabaseManager.getDatabaseConnection();
        } catch (SQLException | NamingException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
}
