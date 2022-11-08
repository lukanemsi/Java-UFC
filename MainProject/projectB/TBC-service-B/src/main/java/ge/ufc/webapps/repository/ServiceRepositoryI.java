package ge.ufc.webapps.repository;

import ge.ufc.webapps.models.BalanceRequest;
import javax.ws.rs.core.Response;

public interface ServiceRepositoryI
{
    Response getUser(int userId);
    Response fillBalance(BalanceRequest balanceRequest);
}
