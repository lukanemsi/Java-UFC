package ge.ufc.webapps.ws;
import java.util.List;
import ge.ufc.webapps.faults.PersonAlreadyExistsException;
import ge.ufc.webapps.faults.PersonNotFoundException;
import ge.ufc.webapps.model.Person;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface PersonServiceI
{

    @WebMethod(operationName = "GetPerson")
    @WebResult(name = "GetPersonResult")
    Person getPerson(@WebParam(name = "ID") int id) throws PersonNotFoundException;

    @WebMethod(operationName = "AddPerson")
    boolean addPerson(@WebParam(name = "Person") Person person) throws PersonAlreadyExistsException;


    @WebMethod(operationName = "UpdatePerson")
    @WebResult(name = "UpdatePersonResult")
    boolean updatePerson(@WebParam(name = "Person") Person person) throws PersonNotFoundException;


    @WebMethod(operationName = "DeletePerson")
    @WebResult(name = "DeletePersonResult")
    boolean deletePerson(@WebParam(name = "ID") int id) throws PersonNotFoundException;


    @WebMethod(operationName = "ListPerson")
    @WebResult(name = "ListPerson")
    List<Person> listPerson();


}
