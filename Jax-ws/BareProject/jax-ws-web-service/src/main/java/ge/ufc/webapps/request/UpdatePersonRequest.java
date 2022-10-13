package ge.ufc.webapps.request;

import ge.ufc.webapps.model.Auth;
import ge.ufc.webapps.model.Person;
import ge.ufc.webapps.ws.PersonService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class UpdatePersonRequest {
    @XmlElement(required = true)
    private int id;
    @XmlElement(name = "first-name", required = true)
    private String name;
    @XmlElement(name = "last-name", required = true)
    private String lastname;
    @XmlElement(required = true)
    private int age;
    @XmlElement
    private Auth auth;

    public Auth getAuth() {
        return auth;
    }
    public Person getPerson()
    {
        Person p = new Person(name,lastname,age);
        p.setId(id);
        return p;
    }
    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
