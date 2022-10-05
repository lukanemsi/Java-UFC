package ge.ufc.webapps.ws;

import ge.ufc.webapps.model.Person;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/personService")
public interface PersonServiceI
{
    @GET
    @Path("/getPerson")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    Response getPerson(@QueryParam("id") int id, @HeaderParam("username") String username, @HeaderParam("password") String password,@Context HttpServletRequest request);

    @POST
    @Path("/addPerson")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Response addPerson(Person person,@HeaderParam("username") String username, @HeaderParam("password") String password,@Context HttpServletRequest request);

    @PUT
    @Path("/updatePerson")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Response updatePerson(Person person,@HeaderParam("username") String username, @HeaderParam("password") String password,@Context HttpServletRequest request);

    @DELETE
    @Path("/deletePerson/{id}")
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Response deletePerson(@PathParam("id") int id,@HeaderParam("username") String username, @HeaderParam("password") String password,@Context HttpServletRequest request);

    @GET
    @Path("/listPersons")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    Response listPersons(@HeaderParam("username") String username, @HeaderParam("password") String password,@Context HttpServletRequest request);
}