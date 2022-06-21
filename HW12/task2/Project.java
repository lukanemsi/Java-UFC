import java.io.*;

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
        printExeFiles(file);

    }
    private static void printExeFiles(File file)
    {
        if(file == null)
            return;
        File[] files = file.listFiles(new ExeFiles());
        if(files == null)
            return;
        for(File f : files)
        {
            if(f.isFile())
                System.out.println("Name: " + f.getName() + " | Path: " + f.getAbsolutePath());
            else if(f.isDirectory())
                printExeFiles(f);
        }
    }
}





