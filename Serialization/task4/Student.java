package Serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

public class Student extends Person
{
    private static final long serialVersionUID = 2L;
    private int course;
    public int gpa;
    private University university;

    public Student(String firstName, String lastName, Date birthdate, Address address,int course, University university) {
        super(firstName, lastName, birthdate,address);
        this.course = course;
        this.university = university;
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.university = new University("KIU",new Address("Georgia","Kutaisi","Street-2"));
    }
    @Override
    public String toString() {
        return "Student{" + super.toString() + " " +
                "course=" + course +
                ", university=" + university +
                '}';
    }
}

