package Serialization;

import java.io.Serializable;

public class University implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String name;
    private transient Address address;

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
}
