package ge.ufc.webservices;

import ge.ufc.webservices.client.Auth;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ClientProperties
{
    private static String url;
    private static int timeout;
    private Auth auth;
    private static volatile Properties properties;
    public ClientProperties() throws IOException
    {
        if(properties == null)
        {
            synchronized (ClientProperties.class) {
                if(properties == null) {
                    Properties properties = new Properties();
                    properties.load(new FileReader("C:\\Users\\Kiu-Student\\web-service-client\\src\\main\\resources\\client.properties"));
                    url = properties.getProperty("url");
                    timeout = Integer.parseInt(properties.getProperty("timeout"));
                    auth = new Auth();

                    auth.setPassword(properties.getProperty("password"));
                    auth.setUsername(properties.getProperty("username"));
                }
            }
        }
    }

    public Auth getAuth() {
        return auth;
    }

    public String getUrl() {
        return url;
    }

    public int getTimeout() {
        return timeout;
    }
}
