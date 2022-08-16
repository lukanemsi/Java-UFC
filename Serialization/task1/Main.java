package Serialization;

import java.io.*;
import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        Person person = new Person("Luka","Nemsi",
                new Date(2020, 7, 16,10,9),
                new Address("Georgia","Tbilisi","Street-1")
            );
        Person copy = copyObject(person);
        System.out.println(person);
        System.out.println(copy);
    }
    public static  <T> T copyObject(T t)
    {
        if(!Serialize.isSerializable(t.getClass()))
        {
            System.out.println("Not serializable");
            return null;
        }
        try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("save_object.txt")))
        {
            stream.writeObject(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream("save_object.txt")))
        {
            return (T) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
