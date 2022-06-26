import java.io.*;
import java.nio.charset.StandardCharsets;

public class Project {
    public static void main(String[] args)
    {
        String text = "luka" + System.getProperty("line.separator") + " лука" + System.getProperty("line.separator") + "  ლუკა";
        String encoding = "UTF-8";
        try(OutputStream os = new FileOutputStream("test.txt"))
        {
            try {
                method(os, text, encoding);
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void method(OutputStream os,String text, String encoding) throws IOException {
        /**
         * writing in file*/
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,encoding)))
        {
            bw.write(text);
        }

        /*** writing in Console*/
        byte[] data = text.getBytes(StandardCharsets.UTF_8);
        os = System.out;
        os.write(data);
    }
}