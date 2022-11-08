package ge.ufc.webapps.repositories;
import ge.ufc.webapps.config.DatabaseManager;
import ge.ufc.webapps.faults.*;
import ge.ufc.webapps.models.User;
import ge.ufc.webapps.util.Validator;
import ge.ufc.webapps.ws.TBCService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.handler.MessageContext;
import java.sql.*;

public class TBCServiceRepository implements TBCServiceRepositoryI
{

    private final Connection connection;
    private static final Logger logger = LogManager.getLogger();
    private final HttpServletRequest request = (HttpServletRequest) TBCService.wsContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);;

    {
        try {
            connection = DatabaseManager.getDatabaseConnection();
        } catch (SQLException | NamingException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String check(int userId) throws InternalErrorException, UserNotFoundException, AgentAuthFailedException, AgentAccessDeniedException {
        Validator.validateRequest(request);
        logger.trace("CheckRequest: userId={}",userId);

        final String statement = "SELECT * FROM users WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(statement))
        {
            preparedStatement.setInt(1, userId);
            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                if(resultSet.next())
                {
                    User user = new User();
                    user.setLastname(resultSet.getString("last_name"));
                    user.setFirstname(resultSet.getString("first_name"));
                    user.setBalance(resultSet.getDouble("balance"));
                    logger.trace("checkResponse : {}",user.responseString());
                    return user.responseString();
                }else
                    throw new UserNotFoundException("Not Found");

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

    @Override
    public long pay(String transactionId, int userId, double amount) throws InternalErrorException, UserNotFoundException, DuplicateException, AmountNotPositiveException, AgentAuthFailedException, AgentAccessDeniedException {
        validatePayService(userId,amount);
        logger.trace("Pay Request: transactionId={}, userId={}, amount={}",transactionId,userId,amount);

        int agent_id = Integer.parseInt(request.getHeader("agent"));
        final String insertTransaction =
                "INSERT INTO " +
                "transactions(agent_id,agent_transaction_id,user_id,amount) VALUES" +
                "( ?, ?, ?, ? )";

        try(PreparedStatement preparedStatement = connection.prepareStatement(insertTransaction,Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setInt(1,agent_id);
            preparedStatement.setString(2,transactionId);
            preparedStatement.setInt(3,userId);
            preparedStatement.setDouble(4,amount);
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys())
            {
                if(resultSet.next())
                {
                    final String updateBalance = "UPDATE users SET balance = ((SELECT balance FROM users WHERE id = ?) + ?) WHERE id = ?";
                    try(PreparedStatement balanceStatement = connection.prepareStatement(updateBalance))
                    {
                        balanceStatement.setInt(1,userId);
                        balanceStatement.setDouble(2,amount);
                        balanceStatement.setInt(3,userId);
                        int row = balanceStatement.executeUpdate();
                        if(row == 0)
                            throw new InternalErrorException("Balance could not updated");
                    }
                    logger.trace("PayResponse : {}",resultSet.getLong(1));
                    return resultSet.getLong(1);
                }
                else
                    throw new InternalErrorException();
            }
        }
        catch (SQLException e)
        {
            if(e.getSQLState().equals("23505"))
                return checkForSameInput(transactionId,userId,amount,agent_id);
            
            throw new InternalErrorException(e);
        }finally
        {
            DatabaseManager.close(connection);
        }
    }
    @Override
    public long status(String transactionId) throws InternalErrorException, TransactionNotFoundException, AgentAuthFailedException, AgentAccessDeniedException {
        Validator.validateRequest(request);
        int agent_id = Integer.parseInt(request.getHeader("agent"));
        logger.trace("Status Request: transactionId={}",transactionId);
        final String findTransaction = "SELECT system_transaction_id FROM transactions WHERE agent_transaction_id = ? AND agent_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(findTransaction))
        {
            preparedStatement.setString(1,transactionId);
            preparedStatement.setInt(2,agent_id);
            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                if(resultSet.next()) {
                    logger.trace("StatusResponse: {}",resultSet.getLong(1));
                    return resultSet.getLong(1);
                }
                throw new TransactionNotFoundException();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new InternalErrorException(e);
        }
        finally {
            DatabaseManager.close(connection);
        }
    }
    private void validatePayService(int userId, double amount) throws AmountNotPositiveException, UserNotFoundException, InternalErrorException, AgentAuthFailedException, AgentAccessDeniedException {
        Validator.validateRequest(request);
        if (amount <= 0)
            throw new AmountNotPositiveException();

        final String validateUser = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(validateUser)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next())
                    throw new UserNotFoundException();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new InternalErrorException(e);
        }
    }
    private long checkForSameInput(String transactionId, int userId, double amount, int agentId) throws InternalErrorException, DuplicateException
    {
        final String selectOldQuery =
                "SELECT system_transaction_id " +
                        "FROM transactions " +
                        "WHERE agent_transaction_id = ? AND user_id = ? AND amount = ? AND agent_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectOldQuery)) {
            preparedStatement.setString(1, transactionId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setInt(4, agentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    return resultSet.getLong(1);
                else
                    throw new DuplicateException();
            }
        } catch (SQLException ex) {
            logger.error(ex);
            throw new InternalErrorException(ex);
        }

    }
}
