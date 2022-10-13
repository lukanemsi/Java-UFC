package ge.ufc.webapps.response;

import ge.ufc.webapps.model.Person;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class GetPersonResponse {
    @XmlElement(name = "person")
    private Person person;

    public Person getEmployee() {
        return person;
    }

    public void setEmployee(Person person) {
        this.person = person;
    }
}
