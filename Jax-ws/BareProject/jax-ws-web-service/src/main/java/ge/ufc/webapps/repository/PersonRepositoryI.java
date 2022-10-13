package ge.ufc.webapps.repository;

import java.util.List;
import ge.ufc.webapps.faults.PersonAlreadyExistsException;
import ge.ufc.webapps.faults.PersonNotFoundException;
import ge.ufc.webapps.model.Person;

public interface PersonRepositoryI
{
    Person getPerson(int id) throws PersonNotFoundException;
    boolean updatePerson(Person person) throws PersonNotFoundException;
    boolean deletePerson(int id) throws PersonNotFoundException;
    boolean addPerson(Person person) throws PersonAlreadyExistsException;
    List<Person> listPerson();
}
