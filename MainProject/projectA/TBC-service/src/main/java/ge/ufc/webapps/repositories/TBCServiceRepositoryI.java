package ge.ufc.webapps.repositories;
import ge.ufc.webapps.faults.*;

public interface TBCServiceRepositoryI
{

    String check(int userId) throws InternalErrorException, UserNotFoundException, AgentAuthFailedException, AgentAccessDeniedException;

    long pay(String transactionId,int userId, double amount) throws InternalErrorException, UserNotFoundException, DuplicateException, AmountNotPositiveException, AgentAuthFailedException, AgentAccessDeniedException;

    long status(String transactionId) throws InternalErrorException, TransactionNotFoundException, AgentAuthFailedException, AgentAccessDeniedException;
}
