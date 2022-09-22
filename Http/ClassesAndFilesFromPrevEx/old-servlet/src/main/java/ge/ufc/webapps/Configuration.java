package ge.ufc.webapps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class Configuration
{
    private static final Logger lgg = LogManager.getLogger(Configuration.class);
    private volatile static Configuration configuration;
    private static final String CONFIG_FILE_LOCATION = "config.properties";
    private static URL url;
    protected long lastModified;
    private static User user;


    static class User
    {
        private final String password;
        private final String username;
        private String path;

        public User(String password, String username) {
            this.password = password;
            this.username = username;
        }
        public boolean validate(String username,String password)
        {
            return this.username.equals(username) && this.password.equals(password);
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    private Configuration(URLConnection conn) throws IOException
    {
        this.lastModified = conn.getLastModified();
        try (InputStream in = conn.getInputStream())
        {
            Properties props = new Properties();
            props.load(conn.getInputStream());
            lgg.info("configuration reloaded");
            setUser(props);
        }
    }
    public static Configuration getConfiguration() throws IOException
    {
        if (url == null)
            url = Configuration.class.getClassLoader().getResource(CONFIG_FILE_LOCATION);
        if (url == null)
            throw new IOException("Configuration file not found");
        URLConnection conn = url.openConnection();
        long lastModified = conn.getLastModified();
        if (configuration == null || lastModified > configuration.lastModified) {
            synchronized (CONFIG_FILE_LOCATION) {
                if (configuration == null || lastModified > configuration.lastModified) {
                    configuration = new Configuration(conn);
                }
            }
        }

        return configuration;
    }
    private static void setUser(Properties properties)
    {
        String name = properties.getProperty("username","");
        String pass = properties.getProperty("password","");
        user = new User(pass,name);
        user.setPath(properties.getProperty("databaseJSON"));
    }
    public static User getUser()
    {
        return user;
    }
}
