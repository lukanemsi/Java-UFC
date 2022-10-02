package ge.ufc.webapps.config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;

public class Configuration
{
    private static final Logger logger = LogManager.getLogger(Configuration.class);
    private volatile static Configuration configuration;
    private static final String CONFIG_FILE_LOCATION = "config.properties";
    private static URL url;
    private long lastModified;
    private static User user;

    public static class User
    {
        private final String password;
        private final String username;
        private String path;
        private final List<String> allowIps = new ArrayList<>();
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

        public List<String> getAllowIps() {
            return allowIps;
        }
    }
    private Configuration(URLConnection conn) throws IOException
    {
        this.lastModified = conn.getLastModified();
        try (InputStream in = conn.getInputStream())
        {
            Properties props = new Properties();
            props.load(in);
            logger.info("configuration reloaded");
            setUser(props);
        }
    }
    public static Configuration getConfiguration() throws IOException
    {
        if (url == null)
            url = Configuration.class.getClassLoader().getResource(CONFIG_FILE_LOCATION);
        if (url == null){
            logger.fatal("Configuration file not found");
            throw new IOException("Configuration file not found");
        }
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
        user.setPath(properties.getProperty("database"));
        String api = properties.getProperty("allowed_ip");
        if(api.contains(";"))
        {
            List<String> apis = Arrays.stream(api.split(";")).collect(Collectors.toList());
            user.getAllowIps().addAll(apis);
        }
        else
        {
            user.getAllowIps().add(api);
        }
        logger.info("user information saved");
    }
    public User getUser()
    {
        return user;
    }
}