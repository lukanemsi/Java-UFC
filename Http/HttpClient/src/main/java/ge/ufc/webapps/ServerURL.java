package ge.ufc.webapps;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ServerURL
{
    private  String[] urls;
    private Long timeOut;
    private final static String FILE_PATH = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\conf\\configForServlet\\config.properties";
    private static class URL
    {
        public static ServerURL serverURL;
        public static ServerURL getServerURL()
        {
            if (serverURL == null)
            {
                synchronized (ServerURL.class)
                {
                    if (serverURL == null)
                        serverURL = new ServerURL();
                }
            }
            return serverURL;
        }
    }
    private ServerURL()
    {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        timeOut = Long.parseLong(properties.getProperty("timeout"));
        urls = properties.getProperty("urls").split(",");
    }

    public static ServerURL getServerURl()
    {
        return URL.getServerURL();
    }

    public  String[] getUrls() {
        return urls;
    }

    public Long getTimeOut() {
        return timeOut;
    }
}
