package ge.ufc.webapps.request;

import ge.ufc.webapps.model.Auth;
import ge.ufc.webapps.model.Person;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class AddPersonRequest {
    @XmlElement(name = "first-name",required = true)
    private String firstname;
    @XmlElement(name = "last-name",required = true)
    private String lastname;
    @XmlElement(required = true)
    private int age;
    @XmlAttribute(required = true)
    private int id;


    @XmlElement
    private Auth auth;
    public Person getPerson()
    {
        Person p = new Person(firstname,lastname,age);
        p.setId(id);
        return p;
    }

    public Auth getAuth() {
        return auth;
    }
    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
