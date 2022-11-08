package ge.ufc.webapps.ws;
import ge.ufc.webapps.models.BalanceRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/service")
public interface TBCServiceBI
{
    @GET
    @Path("/getUser/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUser(@PathParam("user_id") int userId);

    @POST
    @Path("/fillBalance")
    @Consumes(MediaType.APPLICATION_JSON)
    Response fillBalance(BalanceRequest balanceRequest);
}
