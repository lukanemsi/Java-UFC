package ge.ufc.webapps.ws;

import ge.ufc.webapps.config.Configuration;
import ge.ufc.webapps.model.Person;
import ge.ufc.webapps.model.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PersonService implements PersonServiceI
{
    private static final Logger logger = LogManager.getLogger(PersonService.class);
    private final Map<Integer,Person> personHashMap = new HashMap<>();
    private final Configuration.User user;
    private Persons persons;

    public PersonService()
    {
        try {
            user = Configuration.getConfiguration().getUser();
            readFromXMLFile();
        }
        catch (IOException | JAXBException e)
        {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response getPerson(int id,String username,String password,@Context HttpServletRequest request)
    {
        if(!checkIp(request))
            return Response.status(Response.Status.FORBIDDEN).entity("Ip not allowed").build();
        if(!checkAccess(username, password))
            return Response.status(Response.Status.UNAUTHORIZED).entity("Access not allowed").build();

        Person person = personHashMap.get(id);
        if(person == null)
            return Response.status(Response.Status.NOT_FOUND).entity("person doesn't have such id").build();

        logger.info("person information send");
        logger.trace(username + ", " + password  + ", " + request.getRemoteAddr());
        logger.trace(persons.json());
        return Response.status(Response.Status.OK).entity(person).build();
    }

    @Override
    public Response addPerson(Person person,String username,String password,@Context HttpServletRequest request) {
        if(!checkIp(request))
            return Response.status(Response.Status.FORBIDDEN).entity("Ip not allowed").build();
        if(!checkAccess(username, password))
            return Response.status(Response.Status.UNAUTHORIZED).entity("Access not allowed").build();

        if(personHashMap.get(person.getId()) != null)
            return Response.status(Response.Status.CONFLICT).entity("person with this id already exists!").build();

        if(!validatePerson(person))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("person must have all assets required").build();

        personHashMap.put(person.getId(), person);
        persons.setPersonList(new ArrayList<>(personHashMap.values()));
        try {
            writeInXMLFile();
        } catch (JAXBException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("jaxb error").build();
        }
        logger.info("person added in persons.xml");
        logger.trace(username + ", " + password  + ", " + request.getRemoteAddr());
        logger.trace(person.json());
        logger.trace(persons.json());
        return Response.status(Response.Status.OK).entity("Person added!").build();
    }

    @Override
    public Response updatePerson(Person person,String username,String password,@Context HttpServletRequest request) {
        if(!checkIp(request))
            return Response.status(Response.Status.FORBIDDEN).entity("Ip not allowed").build();
        if(!checkAccess(username, password))
            return Response.status(Response.Status.UNAUTHORIZED).entity("Access not allowed").build();
        if(personHashMap.get(person.getId()) == null)
            return Response.status(Response.Status.NOT_FOUND).entity("Person with such id not found").build();

        if(!validatePerson(person))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("person must have all assets required").build();

        personHashMap.put(person.getId(), person);
        persons.setPersonList(new ArrayList<>(personHashMap.values()));
        try {
            writeInXMLFile();
        } catch (JAXBException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("jaxb error").build();
        }
        logger.info("person modified in persons.xml");
        logger.trace(username + ", " + password  + ", " + request.getRemoteAddr());
        logger.trace(person.json());
        logger.trace(persons.json());
        return Response.status(Response.Status.OK).entity("Person Modified").build();
    }

    @Override
    public Response deletePerson(int id,String username,String password,@Context HttpServletRequest request) {
        if(!checkIp(request))
            return Response.status(Response.Status.FORBIDDEN).entity("Ip not allowed").build();
        if(!checkAccess(username, password))
            return Response.status(Response.Status.UNAUTHORIZED).entity("Access not allowed").build();
       if(personHashMap.get(id) == null)
           return Response.status(Response.Status.NOT_FOUND).entity("Person with such id not found").build();
       Person person = personHashMap.remove(id);
       persons.setPersonList(new ArrayList<>(personHashMap.values()));
        try {
            writeInXMLFile();
        } catch (JAXBException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("jaxb error").build();
        }
       logger.info("person deleted in persons.xml");
        logger.trace(username + ", " + password  + ", " + request.getRemoteAddr());
        logger.trace(person.json());
       logger.trace(persons.json());
       return Response.status(Response.Status.OK).entity("Person Deleted").build();
    }

    @Override
    public Response listPersons(String username,String password,@Context HttpServletRequest request)
    {
        if(!checkIp(request))
            return Response.status(Response.Status.FORBIDDEN).entity("Ip not allowed").build();
        if(!checkAccess(username, password))
            return Response.status(Response.Status.UNAUTHORIZED).entity("Access not allowed").build();
        logger.info("persons.xml send");
        logger.trace(username + ", " + password  + ", " + request.getRemoteAddr());
        logger.trace(persons.json());
        return Response.status(Response.Status.OK).entity(persons).build();
    }

    private void writeInXMLFile() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(persons,new File(user.getPath()));

    }

    private void readFromXMLFile() throws JAXBException {
        String path = user.getPath();
        JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        persons = (Persons) unmarshaller.unmarshal(new File(path));
        persons.getPersonList().forEach(p -> personHashMap.put(p.getId(),p));
    }

    private boolean validatePerson(Person person) {return person.getFirstname() != null && person.getLastname() != null && person.getAge() != 0 && person.getId() != -1;}
    private boolean checkAccess(String username,String password)
    {
        return user.validate(username, password);
    }
    private boolean checkIp(HttpServletRequest request)
    {
        List<String> ips = user.getAllowIps();
        return ips.stream().anyMatch(ip -> ip.equals(request.getRemoteAddr()));
    }
}