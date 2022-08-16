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
        Student student = new Student
                        ("Nika","K",
                                new Date(2020, 7, 16,10,9)
                                ,new Address("Georgia","Tbilisi","Street-1"),3,
                                new University("KIU",new Address("Georgia","Kutaisi","Street-2")));
        // exception ?????? ?????? ????????? 1L ?????? ?? ?????????  2L_??
        copyObjectFrom("save_object.txt");
    }
    public static <T> T copyObjectFrom(String fileName)
    {
        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream("save_object.txt")))
        {
            return (T) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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
