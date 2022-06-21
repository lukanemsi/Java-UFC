import java.io.*;

public class Project {
    public static void main(String[] args)
    {
        // მესამე ტასკში ფაილის Path arg_ იდან არ იყო დაკონკრეტებული შესაბამისად აღარ დავამატე. მოსახერხებლობის გამო
        File readF = new File("");
        File writeF = new File("");
        reverseFile(readF,writeF);
    }
    public static void reverseFile(File readFromFile, File writeInFile)
    {
        if(readFromFile == null || writeInFile == null)
        {
            System.out.println("Null input");
            return;
        }
        if(!readFromFile.isFile() || !writeInFile.isFile())
        {
            System.out.println("Wrong files");
            return;
        }
        InputStream input;
        OutputStream output;
        try {
            input = new FileInputStream(readFromFile);
            output = new FileOutputStream(writeInFile);
        }catch (FileNotFoundException e)
        {
            System.out.println("File wasn't found: " + e.getMessage());
            return;
        }
        byte[] b = new byte[(int) readFromFile.length()];
        try {
            input.read(b);
            output.write(reversedByteArray(b));
        }catch (IOException e) {
            System.out.println("Reading or Writing was interrupted: " + e.getMessage());
        }finally
        {
            try {
                input.close();
                output.close();
            } catch (IOException e)
            {
                System.out.println("Files couldn't closed: " + e.getMessage());
            }
        }
    }
    private static byte[] reversedByteArray(byte[] b)
    {
        byte[] result = new byte[b.length];
        for (int i = 0; i < b.length; i++)
        {
            result[b.length - (i + 1)] = b[i];
        }
        return result;
    }
}





