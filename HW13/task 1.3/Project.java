import java.io.*;

public class Project {
    public static void main(String[] args)
    {
        InputStream is;
        try {
            is = new FileInputStream("test.txt");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return;
        }
        String encoding = "UTF-8";
        String result =  method(is,encoding);
        System.out.println(result);
    }
    private static String method(InputStream is, String encoding) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(is,encoding));
        } catch (UnsupportedEncodingException e)
        {
            System.err.println(e.getMessage());
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        if(bufferedReader != null)
        {
            //შესაზლებელია ასევე readLine()_ით გაკეთება
            try {
                while ((i = bufferedReader.read()) != -1)
                {
                    stringBuilder.append((char) i);
                }
            }catch (IOException e) {
                System.err.println(e.getMessage());
            }
            finally
            {
                try
                {
                    bufferedReader.close();
                } catch (IOException e)
                {
                    System.err.println(e.getMessage());
                }
            }
        }
        return stringBuilder.toString();
    }
}





