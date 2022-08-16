package Serialization;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;

public class Main {

    public static void main(String[] args)
    {
        Tester tester = new Tester(100,"hundred");
        write(tester,"test.txt");
        System.out.println(read("test.txt",Tester.class));
    }
    // failidan vkitxulob encodirebul values vuketeb decodings da shemdeg migebuli bytearraydan vkitxulob obieqts
    public static <T> T read(String path,Class<T> classInFile)
    {

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path)))
        {
            byte[] b = Base64.getDecoder().decode(inputStream.readLine());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);
            try(ObjectInputStream stream = new ObjectInputStream(byteArrayInputStream))
            {
                return classInFile.cast(stream.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    // obieqts vinaxav baitereishi shemdeg shesabamis strings vuketeb encodings base64 shi da vinaxav failshi
    public static <T> void write(T t, String path) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream)) {
            oos.writeObject(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path)))
        {
            outputStream.write(Base64.getEncoder().encode(byteArrayOutputStream.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

