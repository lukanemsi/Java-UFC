package ge.ufc.webapps.ws;

import ge.ufc.webapps.faults.PersonAlreadyExistsException;
import ge.ufc.webapps.faults.PersonNotFoundException;
import ge.ufc.webapps.request.*;
import ge.ufc.webapps.response.*;
import javax.jws.*;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.BARE)
@HandlerChain(file = "/handlers.xml")
public interface PersonServiceI
{

    @WebMethod(operationName = "GetPerson")
    @WebResult(name = "GetPersonResult")
    GetPersonResponse getPerson(@WebParam(name = "GetPersonRequest") GetPersonRequest getPersonRequest) throws PersonNotFoundException;

    @WebMethod(operationName = "AddPerson")
    @WebResult(name = "addPersonResult")
    AddPersonResponse addPerson(@WebParam(name = "AddPersonRequest")AddPersonRequest addPersonRequest) throws PersonAlreadyExistsException;

    @WebMethod(operationName = "UpdatePerson")
    @WebResult(name = "UpdatePersonResult")
    UpdatePersonResponse updatePerson(@WebParam(name = "UpdatePersonRequest") UpdatePersonRequest updatePersonRequest) throws PersonNotFoundException;

    @WebMethod(operationName = "DeletePerson")
    @WebResult(name = "DeletePersonResult")
    DeletePersonResponse deletePerson(@WebParam(name = "DeletePersonRequest") DeletePersonRequest deletePersonRequest) throws PersonNotFoundException;


    @WebMethod(operationName = "ListPerson")
    @WebResult(name = "ListPerson")
    ListPersonResponse listPerson(@WebParam(name = "ListPersonRequest") ListPersonRequest listPersonRequest);


}