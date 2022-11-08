package ge.ufc.webapps.ws;
import ge.ufc.webapps.faults.*;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface TBCServiceI
{
    @WebMethod(operationName = "check")
    @WebResult(name = "check")
    String check(@WebParam(name = "userID") int userId) throws InternalErrorException, UserNotFoundException, AgentAuthFailedException, AgentAccessDeniedException;

    @WebMethod(operationName = "pay")
    @WebResult(name = "pay")
    long pay(@WebParam(name = "transaction_id") String transactionId,@WebParam(name = "user_id") int userId,@WebParam(name = "amount") double amount) throws InternalErrorException, UserNotFoundException, DuplicateException, AmountNotPositiveException, AgentAuthFailedException, AgentAccessDeniedException;

    @WebMethod(operationName = "status")
    @WebResult(name = "status")
    long status(@WebParam(name = "transaction_id") String agentTransactionId) throws InternalErrorException, TransactionNotFoundException, AgentAuthFailedException, AgentAccessDeniedException;
}
