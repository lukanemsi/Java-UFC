import java.io.File;
import java.io.FilenameFilter;

public class ExeFiles implements FilenameFilter {
    @Override
    //თუ დირექტორიაა დააბრუნოს True რადგან შემდეგ მის შიგთავსს გადავუყვე, ხოლო თუ ფაილია უნდა დამთავრდეს .exe სიგნატურით
    public boolean accept(File dir, String name)
    {
        if(dir.isDirectory())
            return true;
        return name.endsWith(".exe");
    }
}
