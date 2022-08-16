package Serialization;

import java.util.Date;

public class Student extends Person
{
    private static final long serialVersionUID = 1L;
    private int course;
    private University university;

    public Student(String firstName, String lastName, Date birthdate, Address address,int course, University university) {
        super(firstName, lastName, birthdate,address);
        this.course = course;
        this.university = university;
    }

    @Override
    public String toString() {
        return "Student{" + super.toString() + " " +
                "course=" + course +
                ", university=" + university +
                '}';
    }
}
