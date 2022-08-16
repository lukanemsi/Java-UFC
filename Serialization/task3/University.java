package Serialization;

import java.io.*;

public class University implements Externalizable
{

    private String name;
    private transient Address address;
    public University(){}
    public University(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        address = new Address("Georgia","Kutaisi","Street-2");
    }
}
