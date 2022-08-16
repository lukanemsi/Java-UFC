package Serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

public class Student extends Person
{

    private int course;
    private University university;
    public Student(){}
    public Student(String firstName, String lastName, Date birthdate, Address address,int course, University university) {
        super(firstName, lastName, birthdate,address);
        this.course = course;
        this.university = university;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeInt(course);
        out.writeObject(university);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        course = in.readInt();
        university = (University) in.readObject();
    }
    @Override
    public String toString() {
        return "Student{" + super.toString() + " " +
                "course=" + course +
                ", university=" + university +
                '}';
    }
}
