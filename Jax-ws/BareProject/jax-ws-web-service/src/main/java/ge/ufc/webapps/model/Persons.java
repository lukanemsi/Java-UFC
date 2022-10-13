package ge.ufc.webapps.model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Persons {
    private static final Gson gson = new GsonBuilder().create();
    private List<Person> personList = new ArrayList<>();

    public List<Person> getPersonList() {
        return personList;
    }

    @XmlElement(name="person")
    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public String json()
    {
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return "Persons{" +
                "personList=" + personList +
                '}';
    }
}