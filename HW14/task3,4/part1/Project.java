import java.io.*;
public class Project {
    public static void main(String[] args)
    {

        try(InputStream is = new FileInputStream("test.txt"))
        {
            // მეთოდიდან გამოსროლილი Ex_ის დაჭერა
            try
            {
                String encoding = "UTF-8";
                String result = method(is, encoding);
                System.out.println(result);
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            } catch (IOException | RuntimeException ex) {
                ex.printStackTrace();
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    private static String method(InputStream is, String encoding) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        // try-resource_ით ჩანაცვლება და Exception_ის გარეთ გასროლა
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,encoding)))
        {
            //შესაზლებელია ასევე readLine()_ით გაკეთება
            while ((i = bufferedReader.read()) != -1)
            {
                    stringBuilder.append((char) i);
            }
        }
        return stringBuilder.toString();
    }
}


