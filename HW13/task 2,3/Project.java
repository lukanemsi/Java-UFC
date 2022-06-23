import java.io.*;
import java.nio.charset.StandardCharsets;

public class Project {
    public static void main(String[] args)
    {
        String text = "luka \n лука\nლუკა";
        String encoding = "UTF-8";
        OutputStream os;
        try
        {
            os = new FileOutputStream("test.txt");
        } catch (FileNotFoundException e)
        {
            System.err.println(e.getMessage());
            return;
        }
        method(os,text,encoding);
    }
    private static void method(OutputStream os,String text, String encoding)
    {
        /**
         * writing in file*/
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(os,encoding));
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }
        if(bw != null)
        {
            try
            {
                bw.write(text);
            } catch (IOException e)
            {
                System.err.println(e.getMessage());
            }
            finally
            {
                try
                {
                    bw.close();
                } catch (IOException e)
                {
                    System.err.println(e.getMessage());
                }
            }
        }

        /*** writing in Console*/
        byte[] data = text.getBytes(StandardCharsets.UTF_8);
        os = System.out;
        try
        {
            os.write(data);
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                os.close();
            } catch (IOException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }

}