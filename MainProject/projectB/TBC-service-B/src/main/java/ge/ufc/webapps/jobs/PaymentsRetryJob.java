package ge.ufc.webapps.jobs;

import ge.ufc.webapps.config.DatabaseManager;
import ge.ufc.webapps.models.BalanceRequest;
import ge.ufc.webapps.repository.ServiceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.naming.NamingException;
import java.sql.*;

public class PaymentsRetryJob implements Job
{
    private static final Logger logger = LogManager.getLogger();
    private final Connection connection;
    {
        try {
            connection = DatabaseManager.getDatabaseConnection();
        } catch (SQLException | NamingException e)
        {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        ServiceRepository serviceRepository = new ServiceRepository();

        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM PAYMENTS WHERE status = ?")) {
            ps.setInt(1, 1);
            try(ResultSet resultSet = ps.executeQuery()) {
                while(resultSet.next())
                {
                    BalanceRequest balanceRequest = new BalanceRequest();
                    balanceRequest.setUserId(resultSet.getInt("user_id"));
                    balanceRequest.setPaymentId(String.valueOf(resultSet.getInt("payment_id")));
                    balanceRequest.setAmount(resultSet.getDouble("amount"));

                    serviceRepository.update(connection,balanceRequest,new Timestamp(System.currentTimeMillis()));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        finally {
            DatabaseManager.close(connection);
        }
    }
}
