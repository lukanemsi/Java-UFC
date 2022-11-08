package ge.ufc.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ClientProperties
{
    private final HashMap<String,String> url = new HashMap<>();
    private static final ClientProperties clientProperties = new ClientProperties();
    private final int timeout;
    private ClientProperties()
    {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("C:\\Users\\Kiu-Student\\TBC-http-client\\src\\main\\resources\\configuration.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        url.put("url",properties.getProperty("url"));
        url.put("task1url",properties.getProperty("task1url"));
        url.put("task2url",properties.getProperty("task2url"));
        timeout = Integer.parseInt(properties.getProperty("timeout"));
    }
    public static ClientProperties getInstance()
    {
        return clientProperties;
    }


    public HashMap<String, String> getUrl() {
        return url;
    }
    public int getTimeout() {
        return timeout;
    }
}
