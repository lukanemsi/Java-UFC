import java.io.*;
import java.util.*;

public class Project {
    public static void main(String[] args) throws IOException {
        if(args.length < 1)
        {
            System.out.println("Path expected in args");
            return;
        }
        File file = new File(args[0]);
        if(!file.exists())
        {
            System.out.println("File doesn't exists!");
            return;
        }
        if(!file.isDirectory())
        {
            System.out.println("File is not directory!");
            return;
        }
        printFiles(file);

    }
    private static void printFiles(File file) throws IOException
    {
        if(file == null)
            return;

        File[] files = file.listFiles();
        if(files == null)
            return;

        for(File f : files)
        {
            //თუ ფაილია მაშინ მხოლოდ სახელს და პასს ვბეჭდავთ ხოლო თუ დირექტორიაა იგივეს ვაკეთებთ და შემდეგ გადავყვებით მის შიგთავსს
            if(f.isFile())
                System.out.println("Name: " + f.getName() + " | Path: " + f.getCanonicalPath());
            else
            {
                System.out.println("Name: " + f.getName() + " | Path: " + f.getCanonicalPath());
                printFiles(f);
            }
        }
    }
}





