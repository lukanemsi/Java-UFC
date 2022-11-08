package ge.ufc.webapps.ws;
import ge.ufc.webapps.models.BalanceRequest;
import ge.ufc.webapps.repository.ServiceRepository;
import javax.ws.rs.core.Response;

public class TBCServiceB implements TBCServiceBI
{
    ServiceRepository repository = new ServiceRepository();
    @Override
    public Response getUser(int userId)
    {
        return repository.getUser(userId);
    }
    @Override
    public Response fillBalance(BalanceRequest balanceRequest) {return repository.fillBalance(balanceRequest);}
}

