package Serialization;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String firstName,lastName;
    private Date birthdate;
    private Address address;

    public Person(String firstName, String lastName, Date birthdate, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", address=" + address +
                '}';
    }
}
