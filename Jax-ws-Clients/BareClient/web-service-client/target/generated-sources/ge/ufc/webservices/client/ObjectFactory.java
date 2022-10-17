
package ge.ufc.webservices.client;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ge.ufc.webservices.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddPersonRequest_QNAME = new QName("http://ws.webapps.ufc.ge/", "AddPersonRequest");
    private final static QName _DeletePersonRequest_QNAME = new QName("http://ws.webapps.ufc.ge/", "DeletePersonRequest");
    private final static QName _DeletePersonResult_QNAME = new QName("http://ws.webapps.ufc.ge/", "DeletePersonResult");
    private final static QName _GetPersonRequest_QNAME = new QName("http://ws.webapps.ufc.ge/", "GetPersonRequest");
    private final static QName _GetPersonResult_QNAME = new QName("http://ws.webapps.ufc.ge/", "GetPersonResult");
    private final static QName _ListPerson_QNAME = new QName("http://ws.webapps.ufc.ge/", "ListPerson");
    private final static QName _ListPersonRequest_QNAME = new QName("http://ws.webapps.ufc.ge/", "ListPersonRequest");
    private final static QName _PersonAlreadyExistsException_QNAME = new QName("http://ws.webapps.ufc.ge/", "PersonAlreadyExistsException");
    private final static QName _PersonNotFoundException_QNAME = new QName("http://ws.webapps.ufc.ge/", "PersonNotFoundException");
    private final static QName _UpdatePersonRequest_QNAME = new QName("http://ws.webapps.ufc.ge/", "UpdatePersonRequest");
    private final static QName _UpdatePersonResult_QNAME = new QName("http://ws.webapps.ufc.ge/", "UpdatePersonResult");
    private final static QName _AddPersonResult_QNAME = new QName("http://ws.webapps.ufc.ge/", "addPersonResult");
    private final static QName _Auth_QNAME = new QName("http://ws.webapps.ufc.ge/", "auth");
    private final static QName _Person_QNAME = new QName("http://ws.webapps.ufc.ge/", "person");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ge.ufc.webservices.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddPersonRequest }
     * 
     */
    public AddPersonRequest createAddPersonRequest() {
        return new AddPersonRequest();
    }

    /**
     * Create an instance of {@link DeletePersonRequest }
     * 
     */
    public DeletePersonRequest createDeletePersonRequest() {
        return new DeletePersonRequest();
    }

    /**
     * Create an instance of {@link DeletePersonResponse }
     * 
     */
    public DeletePersonResponse createDeletePersonResponse() {
        return new DeletePersonResponse();
    }

    /**
     * Create an instance of {@link GetPersonRequest }
     * 
     */
    public GetPersonRequest createGetPersonRequest() {
        return new GetPersonRequest();
    }

    /**
     * Create an instance of {@link GetPersonResponse }
     * 
     */
    public GetPersonResponse createGetPersonResponse() {
        return new GetPersonResponse();
    }

    /**
     * Create an instance of {@link ListPersonResponse }
     * 
     */
    public ListPersonResponse createListPersonResponse() {
        return new ListPersonResponse();
    }

    /**
     * Create an instance of {@link ListPersonRequest }
     * 
     */
    public ListPersonRequest createListPersonRequest() {
        return new ListPersonRequest();
    }

    /**
     * Create an instance of {@link PersonAlreadyExistsException }
     * 
     */
    public PersonAlreadyExistsException createPersonAlreadyExistsException() {
        return new PersonAlreadyExistsException();
    }

    /**
     * Create an instance of {@link PersonNotFoundException }
     * 
     */
    public PersonNotFoundException createPersonNotFoundException() {
        return new PersonNotFoundException();
    }

    /**
     * Create an instance of {@link UpdatePersonRequest }
     * 
     */
    public UpdatePersonRequest createUpdatePersonRequest() {
        return new UpdatePersonRequest();
    }

    /**
     * Create an instance of {@link UpdatePersonResponse }
     * 
     */
    public UpdatePersonResponse createUpdatePersonResponse() {
        return new UpdatePersonResponse();
    }

    /**
     * Create an instance of {@link AddPersonResponse }
     * 
     */
    public AddPersonResponse createAddPersonResponse() {
        return new AddPersonResponse();
    }

    /**
     * Create an instance of {@link Auth }
     * 
     */
    public Auth createAuth() {
        return new Auth();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPersonRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddPersonRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "AddPersonRequest")
    public JAXBElement<AddPersonRequest> createAddPersonRequest(AddPersonRequest value) {
        return new JAXBElement<AddPersonRequest>(_AddPersonRequest_QNAME, AddPersonRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePersonRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeletePersonRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "DeletePersonRequest")
    public JAXBElement<DeletePersonRequest> createDeletePersonRequest(DeletePersonRequest value) {
        return new JAXBElement<DeletePersonRequest>(_DeletePersonRequest_QNAME, DeletePersonRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "DeletePersonResult")
    public JAXBElement<DeletePersonResponse> createDeletePersonResult(DeletePersonResponse value) {
        return new JAXBElement<DeletePersonResponse>(_DeletePersonResult_QNAME, DeletePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPersonRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "GetPersonRequest")
    public JAXBElement<GetPersonRequest> createGetPersonRequest(GetPersonRequest value) {
        return new JAXBElement<GetPersonRequest>(_GetPersonRequest_QNAME, GetPersonRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "GetPersonResult")
    public JAXBElement<GetPersonResponse> createGetPersonResult(GetPersonResponse value) {
        return new JAXBElement<GetPersonResponse>(_GetPersonResult_QNAME, GetPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListPersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "ListPerson")
    public JAXBElement<ListPersonResponse> createListPerson(ListPersonResponse value) {
        return new JAXBElement<ListPersonResponse>(_ListPerson_QNAME, ListPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPersonRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListPersonRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "ListPersonRequest")
    public JAXBElement<ListPersonRequest> createListPersonRequest(ListPersonRequest value) {
        return new JAXBElement<ListPersonRequest>(_ListPersonRequest_QNAME, ListPersonRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonAlreadyExistsException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PersonAlreadyExistsException }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "PersonAlreadyExistsException")
    public JAXBElement<PersonAlreadyExistsException> createPersonAlreadyExistsException(PersonAlreadyExistsException value) {
        return new JAXBElement<PersonAlreadyExistsException>(_PersonAlreadyExistsException_QNAME, PersonAlreadyExistsException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonNotFoundException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PersonNotFoundException }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "PersonNotFoundException")
    public JAXBElement<PersonNotFoundException> createPersonNotFoundException(PersonNotFoundException value) {
        return new JAXBElement<PersonNotFoundException>(_PersonNotFoundException_QNAME, PersonNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdatePersonRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "UpdatePersonRequest")
    public JAXBElement<UpdatePersonRequest> createUpdatePersonRequest(UpdatePersonRequest value) {
        return new JAXBElement<UpdatePersonRequest>(_UpdatePersonRequest_QNAME, UpdatePersonRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdatePersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "UpdatePersonResult")
    public JAXBElement<UpdatePersonResponse> createUpdatePersonResult(UpdatePersonResponse value) {
        return new JAXBElement<UpdatePersonResponse>(_UpdatePersonResult_QNAME, UpdatePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddPersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "addPersonResult")
    public JAXBElement<AddPersonResponse> createAddPersonResult(AddPersonResponse value) {
        return new JAXBElement<AddPersonResponse>(_AddPersonResult_QNAME, AddPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Auth }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Auth }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "auth")
    public JAXBElement<Auth> createAuth(Auth value) {
        return new JAXBElement<Auth>(_Auth_QNAME, Auth.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Person }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Person }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "person")
    public JAXBElement<Person> createPerson(Person value) {
        return new JAXBElement<Person>(_Person_QNAME, Person.class, null, value);
    }

}
