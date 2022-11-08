package ge.ufc.webapps.ws;
import ge.ufc.webapps.faults.*;
import ge.ufc.webapps.repositories.TBCServiceRepository;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService(endpointInterface = "ge.ufc.webapps.ws.TBCServiceI")
public class TBCService implements TBCServiceI
{

    @Resource
    public static WebServiceContext wsContext;

    private TBCServiceRepository repository;
    @Override
    public String check(int userId) throws InternalErrorException, UserNotFoundException, AgentAuthFailedException, AgentAccessDeniedException {
        repository = new TBCServiceRepository();
        return repository.check(userId);
    }

    @Override
    public long pay(String transactionId, int userId, double amount) throws InternalErrorException, UserNotFoundException, DuplicateException, AmountNotPositiveException, AgentAuthFailedException, AgentAccessDeniedException {
        repository = new TBCServiceRepository();
        return repository.pay(transactionId,userId,amount);
    }


    @Override
    public long status(String transactionId) throws InternalErrorException, TransactionNotFoundException, AgentAuthFailedException, AgentAccessDeniedException {
        repository = new TBCServiceRepository();
        return repository.status(transactionId);
    }
}
