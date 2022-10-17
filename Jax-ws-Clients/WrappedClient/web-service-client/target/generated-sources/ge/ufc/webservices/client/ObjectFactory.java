
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

    private final static QName _AddPerson_QNAME = new QName("http://ws.webapps.ufc.ge/", "AddPerson");
    private final static QName _AddPersonResponse_QNAME = new QName("http://ws.webapps.ufc.ge/", "AddPersonResponse");
    private final static QName _DeletePerson_QNAME = new QName("http://ws.webapps.ufc.ge/", "DeletePerson");
    private final static QName _DeletePersonResponse_QNAME = new QName("http://ws.webapps.ufc.ge/", "DeletePersonResponse");
    private final static QName _GetPerson_QNAME = new QName("http://ws.webapps.ufc.ge/", "GetPerson");
    private final static QName _GetPersonResponse_QNAME = new QName("http://ws.webapps.ufc.ge/", "GetPersonResponse");
    private final static QName _ListPerson_QNAME = new QName("http://ws.webapps.ufc.ge/", "ListPerson");
    private final static QName _ListPersonResponse_QNAME = new QName("http://ws.webapps.ufc.ge/", "ListPersonResponse");
    private final static QName _PersonAlreadyExistsException_QNAME = new QName("http://ws.webapps.ufc.ge/", "PersonAlreadyExistsException");
    private final static QName _PersonNotFoundException_QNAME = new QName("http://ws.webapps.ufc.ge/", "PersonNotFoundException");
    private final static QName _UpdatePerson_QNAME = new QName("http://ws.webapps.ufc.ge/", "UpdatePerson");
    private final static QName _UpdatePersonResponse_QNAME = new QName("http://ws.webapps.ufc.ge/", "UpdatePersonResponse");
    private final static QName _Person_QNAME = new QName("http://ws.webapps.ufc.ge/", "person");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ge.ufc.webservices.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddPerson }
     * 
     */
    public AddPerson createAddPerson() {
        return new AddPerson();
    }

    /**
     * Create an instance of {@link AddPersonResponse }
     * 
     */
    public AddPersonResponse createAddPersonResponse() {
        return new AddPersonResponse();
    }

    /**
     * Create an instance of {@link DeletePerson }
     * 
     */
    public DeletePerson createDeletePerson() {
        return new DeletePerson();
    }

    /**
     * Create an instance of {@link DeletePersonResponse }
     * 
     */
    public DeletePersonResponse createDeletePersonResponse() {
        return new DeletePersonResponse();
    }

    /**
     * Create an instance of {@link GetPerson }
     * 
     */
    public GetPerson createGetPerson() {
        return new GetPerson();
    }

    /**
     * Create an instance of {@link GetPersonResponse }
     * 
     */
    public GetPersonResponse createGetPersonResponse() {
        return new GetPersonResponse();
    }

    /**
     * Create an instance of {@link ListPerson }
     * 
     */
    public ListPerson createListPerson() {
        return new ListPerson();
    }

    /**
     * Create an instance of {@link ListPersonResponse }
     * 
     */
    public ListPersonResponse createListPersonResponse() {
        return new ListPersonResponse();
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
     * Create an instance of {@link UpdatePerson }
     * 
     */
    public UpdatePerson createUpdatePerson() {
        return new UpdatePerson();
    }

    /**
     * Create an instance of {@link UpdatePersonResponse }
     * 
     */
    public UpdatePersonResponse createUpdatePersonResponse() {
        return new UpdatePersonResponse();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPerson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddPerson }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "AddPerson")
    public JAXBElement<AddPerson> createAddPerson(AddPerson value) {
        return new JAXBElement<AddPerson>(_AddPerson_QNAME, AddPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddPersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "AddPersonResponse")
    public JAXBElement<AddPersonResponse> createAddPersonResponse(AddPersonResponse value) {
        return new JAXBElement<AddPersonResponse>(_AddPersonResponse_QNAME, AddPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePerson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeletePerson }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "DeletePerson")
    public JAXBElement<DeletePerson> createDeletePerson(DeletePerson value) {
        return new JAXBElement<DeletePerson>(_DeletePerson_QNAME, DeletePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "DeletePersonResponse")
    public JAXBElement<DeletePersonResponse> createDeletePersonResponse(DeletePersonResponse value) {
        return new JAXBElement<DeletePersonResponse>(_DeletePersonResponse_QNAME, DeletePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPerson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPerson }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "GetPerson")
    public JAXBElement<GetPerson> createGetPerson(GetPerson value) {
        return new JAXBElement<GetPerson>(_GetPerson_QNAME, GetPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetPersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "GetPersonResponse")
    public JAXBElement<GetPersonResponse> createGetPersonResponse(GetPersonResponse value) {
        return new JAXBElement<GetPersonResponse>(_GetPersonResponse_QNAME, GetPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPerson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListPerson }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "ListPerson")
    public JAXBElement<ListPerson> createListPerson(ListPerson value) {
        return new JAXBElement<ListPerson>(_ListPerson_QNAME, ListPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListPersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "ListPersonResponse")
    public JAXBElement<ListPersonResponse> createListPersonResponse(ListPersonResponse value) {
        return new JAXBElement<ListPersonResponse>(_ListPersonResponse_QNAME, ListPersonResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePerson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdatePerson }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "UpdatePerson")
    public JAXBElement<UpdatePerson> createUpdatePerson(UpdatePerson value) {
        return new JAXBElement<UpdatePerson>(_UpdatePerson_QNAME, UpdatePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdatePersonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.webapps.ufc.ge/", name = "UpdatePersonResponse")
    public JAXBElement<UpdatePersonResponse> createUpdatePersonResponse(UpdatePersonResponse value) {
        return new JAXBElement<UpdatePersonResponse>(_UpdatePersonResponse_QNAME, UpdatePersonResponse.class, null, value);
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
