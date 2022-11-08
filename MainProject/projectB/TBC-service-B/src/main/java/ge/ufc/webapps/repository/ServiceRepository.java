package ge.ufc.webapps.repository;

import com.google.gson.Gson;
import ge.ufc.webapps.client.*;
import ge.ufc.webapps.config.DatabaseManager;
import ge.ufc.webapps.config.Server;
import ge.ufc.webapps.models.BalanceRequest;
import ge.ufc.webapps.models.JsonResponse;
import ge.ufc.webapps.models.User;
import jakarta.xml.ws.WebServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import javax.naming.NamingException;
import javax.ws.rs.core.Response;
import java.net.SocketTimeoutException;
import java.sql.*;

public class ServiceRepository implements ServiceRepositoryI
{
    private static final Logger logger = LogManager.getLogger();
    private static final TBCServiceI serviceProxy = Server.getProxy();
    private static final Gson gson = new Gson();
    static
    {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            logger.info("JOB STARTED!");
        } catch (SchedulerException e)
        {
            logger.error(e.getMessage(), e);
        }
    }
    @Override
    public Response getUser(int userId)
    {
        logger.info("getUserRequest: userId={}",userId);
        User user;
        try {
            String result = serviceProxy.check(userId);
            String[] initials = result.split(" ");
            user = new User(initials[0],initials[1],Double.parseDouble(initials[2]));
        } catch (AgentAccessDeniedException_Exception | AgentAuthFailedException_Exception | InternalErrorException_Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (UserNotFoundException_Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }catch (WebServiceException e)
        {
            if(e.getCause() != null && e.getCause().getClass().isAssignableFrom(SocketTimeoutException.class))
                return Response.status(Response.Status.REQUEST_TIMEOUT).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).entity(gson.toJson(user)).build();
    }
    @Override
    public Response fillBalance(BalanceRequest balanceRequest)
    {
        logger.info("fillBalanceRequest: paymentId={}, userId={}, amount={}"
                        ,balanceRequest.getPaymentId(),balanceRequest.getUserId(),balanceRequest.getAmount());

        Timestamp requestTimestamp = new Timestamp(System.currentTimeMillis());
        Connection connection;
        try{
            connection = DatabaseManager.getDatabaseConnection();
        } catch (SQLException | NamingException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        Response res;
        if((res = updateIfExists(connection,balanceRequest,requestTimestamp))  != null)
        {
            DatabaseManager.close(connection);
            return res;
        }
        return insert(connection,balanceRequest,requestTimestamp);
    }
    private Response insert(Connection connection,BalanceRequest balanceRequest,Timestamp requestTimestamp)
    {
        byte status = 2;
        short code = 0;
        long systemTransactionId = 0;
        Timestamp responseTimestamp = null;

        try
        {
            systemTransactionId = serviceProxy.pay(balanceRequest.getPaymentId(), balanceRequest.getUserId(), balanceRequest.getAmount());
            responseTimestamp = new Timestamp(System.currentTimeMillis());
            status = 0;
            code = (short) Response.Status.OK.getStatusCode();
            return Response.status(Response.Status.OK).entity(new JsonResponse(systemTransactionId,code).toJson()).build();
        }  catch (AmountNotPositiveException_Exception e)
        {
            code = (short) Response.Status.BAD_REQUEST.getStatusCode();
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (DuplicateException_Exception e)
        {
            code = (short) Response.Status.CONFLICT.getStatusCode();
            return Response.status(Response.Status.CONFLICT).build();
        } catch (UserNotFoundException_Exception e)
        {
            code = (short) Response.Status.NOT_FOUND.getStatusCode();
            return Response.status(Response.Status.NOT_FOUND).build();
        }catch (AgentAuthFailedException_Exception | AgentAccessDeniedException_Exception e)
        {
            code = (short) Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        catch (InternalErrorException_Exception |  WebServiceException e)
        {
            status = 1;
            if(e.getCause() != null && e.getCause().getClass().isAssignableFrom(SocketTimeoutException.class))
            {
                code = (short) Response.Status.REQUEST_TIMEOUT.getStatusCode();
                return Response.status(Response.Status.REQUEST_TIMEOUT).build();
            }
            else{
                code = (short) Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        finally
        {
            if(responseTimestamp == null)
                responseTimestamp = new Timestamp(System.currentTimeMillis());

            String statement = "INSERT INTO payments VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement prepareStatement = connection.prepareStatement(statement))
            {
                prepareStatement.setInt(1,Integer.parseInt(balanceRequest.getPaymentId()));
                prepareStatement.setInt(2,balanceRequest.getUserId());
                prepareStatement.setDouble(3,balanceRequest.getAmount());
                prepareStatement.setLong(4,systemTransactionId);
                prepareStatement.setTimestamp(5,requestTimestamp);
                prepareStatement.setTimestamp(6,responseTimestamp);
                prepareStatement.setShort(7, code);
                prepareStatement.setShort(8,status);
                prepareStatement.executeUpdate();

            }catch (SQLException er)
            {
                logger.error(er);
            }
            finally
            {
                DatabaseManager.close(connection);
            }
        }
    }
    private Response updateIfExists(Connection connection, BalanceRequest balanceRequest,Timestamp requestTimestamp)
    {
        try {
            String checkInputStatement = "SELECT * FROM PAYMENTS WHERE payment_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(checkInputStatement);
            preparedStatement.setInt(1, Integer.parseInt(balanceRequest.getPaymentId()));
            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                if(resultSet.next())
                {
                    if(resultSet.getInt("user_id") != balanceRequest.getUserId() || resultSet.getDouble("amount") != balanceRequest.getAmount())
                        return Response.status(Response.Status.CONFLICT).build();

                    if(resultSet.getShort("status") == 1)
                        return update(connection,balanceRequest,requestTimestamp);

                    short code = resultSet.getShort("code");
                    JsonResponse jsonResponse = new JsonResponse(resultSet.getLong("transaction_id"),code);
                    String response = "";
                    if(code == 200)
                        response = jsonResponse.toJson();
                    return Response.status(code).entity(response).build();
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
        return null;
    }
    public Response update(Connection connection,BalanceRequest balanceRequest,Timestamp requestTimestamp) throws SQLException
    {
        short newStatus = 2;
        short newCode = 0;
        Timestamp responseTimestamp = null;
        try {
            long sysTransactionId = serviceProxy.pay(balanceRequest.getPaymentId(), balanceRequest.getUserId(), balanceRequest.getAmount());
            responseTimestamp = new Timestamp(System.currentTimeMillis());
            newStatus = 0;
            newCode = (short) Response.Status.OK.getStatusCode();
            return Response.status(Response.Status.OK).entity(new JsonResponse(sysTransactionId,newCode).toJson()).build();
        } catch (AgentAccessDeniedException_Exception | AgentAuthFailedException_Exception e) {
            newCode = (short) Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AmountNotPositiveException_Exception e) {
            newCode = (short) Response.Status.BAD_REQUEST.getStatusCode();
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (DuplicateException_Exception e) {
            newCode = (short) Response.Status.CONFLICT.getStatusCode();
            return Response.status(Response.Status.CONFLICT).build();
        } catch (UserNotFoundException_Exception e) {
            newCode = (short) Response.Status.NOT_FOUND.getStatusCode();
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (WebServiceException | InternalErrorException_Exception e) {
            newStatus = 1;
            if(e.getCause() != null && e.getCause().getClass().isAssignableFrom(SocketTimeoutException.class))
            {
                newCode = (short) Response.Status.REQUEST_TIMEOUT.getStatusCode();
                return Response.status(Response.Status.REQUEST_TIMEOUT).build();
            }
            else{
                newCode = (short) Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }finally
        {
            if(responseTimestamp == null)
                responseTimestamp = new Timestamp(System.currentTimeMillis());

            String update = "UPDATE payments SET code = ?, status = ?, request_date = ?, response_date = ? WHERE payment_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(update);
            updateStatement.setShort(1,newCode);
            updateStatement.setShort(2,newStatus);
            updateStatement.setTimestamp(3,requestTimestamp);
            updateStatement.setTimestamp(4,responseTimestamp);
            updateStatement.setInt(5, Integer.parseInt(balanceRequest.getPaymentId()));
            updateStatement.executeUpdate();
        }
    }
}