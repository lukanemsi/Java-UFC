package ge.ufc.webapps.ws;
import ge.ufc.webapps.faults.PersonAlreadyExistsException;
import ge.ufc.webapps.faults.PersonNotFoundException;
import ge.ufc.webapps.model.Person;
import ge.ufc.webapps.repository.PersonRepository;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import java.util.List;

@WebService(endpointInterface = "ge.ufc.webapps.ws.PersonServiceI")
public class PersonService implements PersonServiceI
{
    @Resource
    public static WebServiceContext wsContext;

    private final PersonRepository personRepository = new PersonRepository();
    @Override
    public Person getPerson(int id) throws PersonNotFoundException
    {
        return personRepository.getPerson(id);
    }

    @Override
    public boolean addPerson(Person person) throws PersonAlreadyExistsException {
        return personRepository.addPerson(person);
    }

    @Override
    public boolean updatePerson(Person person) throws PersonNotFoundException {
       return personRepository.updatePerson(person);
    }

    @Override
    public boolean deletePerson(int id) throws PersonNotFoundException {
        return personRepository.deletePerson(id);
    }

    @Override
    public List<Person> listPerson()
    {
        return personRepository.listPerson();
    }

}
