package ge.ufc.webapps.repository;

import ge.ufc.webapps.config.Configuration;
import ge.ufc.webapps.faults.GeneralErrorException;
import ge.ufc.webapps.faults.PersonAlreadyExistsException;
import ge.ufc.webapps.faults.PersonNotFoundException;
import ge.ufc.webapps.model.Person;
import ge.ufc.webapps.model.Persons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonRepository implements PersonRepositoryI
{
    private static final Logger logger = LogManager.getLogger();
    private final Map<Integer,Person> personHashMap = new HashMap<>();
    private final Configuration.User user;
    private Persons persons;

    public PersonRepository()
    {
        logger.trace("Start init");
        try {
            user = Configuration.getConfiguration().getUser();
            if(user == null)
                throw new NullPointerException();
            readFromXMLFile();
        }
        catch (IOException | JAXBException | NullPointerException e)
        {
            logger.error(e.getMessage());
            throw new GeneralErrorException();
        }
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundException
    {

        logger.trace(id);
        logger.trace(personHashMap.get(id));
        if(personHashMap.get(id) != null)
            return personHashMap.get(id);

        throw new PersonNotFoundException();
    }

    @Override
    public boolean addPerson(Person person) throws PersonAlreadyExistsException {

        if(personHashMap.get(person.getId()) != null)
            throw new PersonAlreadyExistsException();
        personHashMap.put(person.getId(),person);
        persons.setPersonList(new ArrayList<>(personHashMap.values()));
        try {
            writeInXMLFile();
        } catch (JAXBException e) {
            logger.error("Error in XML");
            throw new GeneralErrorException();
        }
        logger.trace(person);
        return true;
    }

    @Override
    public boolean updatePerson(Person person) throws PersonNotFoundException {

        if(personHashMap.get(person.getId()) == null)
            throw new PersonNotFoundException();
        personHashMap.put(person.getId(),person);
        persons.setPersonList(new ArrayList<>(personHashMap.values()));
        try {
            writeInXMLFile();
        } catch (JAXBException e) {
            logger.error("Error in XML");
            throw new GeneralErrorException();
        }

        logger.trace(person);
        return true;
    }

    @Override
    public boolean deletePerson(int id) throws PersonNotFoundException {

        if(personHashMap.get(id) == null)
            throw new PersonNotFoundException();
        Person deletedP = personHashMap.remove(id);
        persons.setPersonList(new ArrayList<>(personHashMap.values()));
        try {
            writeInXMLFile();
        } catch (JAXBException e) {
            logger.error("Error in XML");
            throw new GeneralErrorException();
        }
        logger.trace(id);
        logger.trace(deletedP);
        return true;
    }

    @Override
    public List<Person> listPerson()
    {

        logger.trace(personHashMap.values());
        return new ArrayList<>(personHashMap.values());
    }

    private void writeInXMLFile() throws JAXBException
    {
        JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(persons,new File(user.getPath()));
    }

    private void readFromXMLFile() throws JAXBException
    {
        JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        persons = (Persons) unmarshaller.unmarshal(new File(user.getPath()));
        persons.getPersonList().forEach(p -> personHashMap.put(p.getId(),p));
    }

}
