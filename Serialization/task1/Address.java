package Serialization;

import java.io.Serializable;

public class Address implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String country, city, street;

    public Address(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
