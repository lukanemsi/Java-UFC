package ge.ufc.webapps.ws;

import ge.ufc.webapps.config.Configuration;
import ge.ufc.webapps.faults.*;
import ge.ufc.webapps.model.Auth;
import ge.ufc.webapps.model.Person;
import ge.ufc.webapps.repository.PersonRepository;
import ge.ufc.webapps.request.*;
import ge.ufc.webapps.response.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.io.IOException;
import java.util.List;

@WebService(endpointInterface = "ge.ufc.webapps.ws.PersonServiceI")
public class PersonService implements PersonServiceI {

    private PersonRepository personRepository = new PersonRepository();
    private final Logger logger = LogManager.getLogger();
    private Configuration.User user;
    @Resource
    public WebServiceContext wsContext;

    {
        try {
            user = Configuration.getConfiguration().getUser();
        } catch (IOException e) {
            throw new GeneralErrorException();
        }
    }


    @Override
    public UpdatePersonResponse updatePerson(UpdatePersonRequest updatePersonRequest) throws PersonNotFoundException {
        checkIp();
        logIn(updatePersonRequest.getAuth());
        boolean res = personRepository.updatePerson(updatePersonRequest.getPerson());
        UpdatePersonResponse updatePersonResponse = new UpdatePersonResponse();
        updatePersonResponse.setUpdated(res);

        logger.trace(updatePersonRequest);
        logger.trace(updatePersonResponse);
        return updatePersonResponse;
    }

    @Override
    public GetPersonResponse getPerson(GetPersonRequest getPersonRequest) throws PersonNotFoundException {
        checkIp();
        logIn(getPersonRequest.getAuth());
        Person res = personRepository.getPerson(getPersonRequest.getId());
        GetPersonResponse getPersonResponse = new GetPersonResponse();
        getPersonResponse.setEmployee(res);

        logger.trace(getPersonRequest);
        logger.trace(getPersonResponse);
        return getPersonResponse;
    }

    @Override
    public AddPersonResponse addPerson(AddPersonRequest addPersonRequest) throws PersonAlreadyExistsException {
        checkIp();
        logIn(addPersonRequest.getAuth());
        boolean res = personRepository.addPerson(addPersonRequest.getPerson());
        AddPersonResponse addPersonResponse = new AddPersonResponse();
        addPersonResponse.setAdded(res);

        logger.trace(addPersonRequest);
        logger.trace(addPersonResponse);
        return addPersonResponse;
    }


    @Override
    public DeletePersonResponse deletePerson(DeletePersonRequest deletePersonRequest) throws PersonNotFoundException {
        checkIp();
        logIn(deletePersonRequest.getAuth());
        boolean res = personRepository.deletePerson(deletePersonRequest.getId());
        DeletePersonResponse deletePersonResponse = new DeletePersonResponse();
        deletePersonResponse.setDeleted(res);

        logger.trace(deletePersonRequest);
        logger.trace(deletePersonResponse);
        return deletePersonResponse;
    }

    @Override
    public ListPersonResponse listPerson(ListPersonRequest listPersonRequest) {
        checkIp();
        logIn(listPersonRequest.getAuth());
        List<Person> res = personRepository.listPerson();
        ListPersonResponse listPersonResponse = new ListPersonResponse();
        listPersonResponse.setPersonList(res);

        logger.trace(listPersonRequest);
        logger.trace(listPersonResponse);
        return listPersonResponse;
    }

    private void checkIp() {
        HttpServletRequest request = (HttpServletRequest) wsContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);
        List<String> ips = user.getAllowIps();
        if (ips.stream().noneMatch(ip -> ip.equals(request.getRemoteAddr()))) {
            throw new AccessForbiddenException();
        }
    }

    private void logIn(Auth auth)
    {
        String pass = auth.getPassword();
        String username = auth.getUsername();
        if (username == null || pass == null)
            throw new ClientUnauthorizedException();
        if(!user.validate(username,pass))
            throw new ClientUnauthorizedException();
    }
}
