package ge.ufc.webapps.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class Configuration
{
    private static final Logger logger = LogManager.getLogger(Configuration.class);
    private volatile static Configuration configuration;
    private static final String CONFIG_FILE_LOCATION = "config.properties";
    private static URL url;
    private final long lastModified;
    private static Agent agent;

    public static class Agent
    {
        private final String id;
        private final String password;
        private String url;
        private int timeout;

        public Agent(String id,String password) {
            this.password = password;
            this.id = id;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

        public String getUrl() {
            return url;
        }

        public int getTimeout() {
            return timeout;
        }

        public String getId() {
            return id;
        }

        public String getPassword() {
            return password;
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
            setAgent(props);
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
    private static void setAgent(Properties properties)
    {
        String id = properties.getProperty("agent_id","");
        String pass = properties.getProperty("password","");
        String url = properties.getProperty("url","");
        int timeout = Integer.parseInt(properties.getProperty("timeout","0"));
        agent = new Agent(id,pass);
        agent.setUrl(url);
        agent.setTimeout(timeout);
        logger.info("Property file read");
    }
    public Agent getAgent()
    {
        return agent;
    }

}
