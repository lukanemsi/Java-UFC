import java.io.*;

public class Project {
    public static void main(String[] args)
    {
        //მიმდინარე დირეცტორია
        File readF = new File("./test.txt");
        File writeF = new File("/./folder/test1.txt");
        copy(readF,writeF);
    }
    private static void copy(File readFromFile, File writeInFile)
    {
        if(readFromFile == null || writeInFile == null)
        {
            System.out.println("Null inputs");
            return;
        }
        if(!readFromFile.isFile() || !writeInFile.isFile())
        {
            System.out.println("Not file inputs");
            return;
        }
        BufferedInputStream inputStream;
        BufferedOutputStream outputStream;
        try{
            inputStream = new BufferedInputStream(new FileInputStream(readFromFile));
            outputStream = new BufferedOutputStream(new FileOutputStream(writeInFile));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return;
        }
        int b;
        int counter = 0;
        try {
            while (true)
            {
                if (-1 == (b = inputStream.read()))
                    break;
                outputStream.write(b);
                counter++;
                if (counter % 3 == 0) {
                    outputStream.flush();
                    System.out.println("flushed");
                    Thread.sleep(10_000);

                }
            }
        }catch (IOException | InterruptedException e )
        {
            e.printStackTrace();
        }
        finally
        {
            try{
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}





