package ge.ufc.webapps.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person", propOrder = { "firstname", "lastname", "age" })
public class Person
{
    @XmlElement(name = "first-name")
    protected String firstname;
    @XmlElement(name = "last-name")
    protected String lastname;
    @XmlElement
    protected int age;
    @XmlAttribute
    protected int id = -1;
    public Person() {}
    public Person(String firstname,String lastname,int age)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }
}
