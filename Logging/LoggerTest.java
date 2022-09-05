import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerTest
{
    static Logger logger = LogManager.getLogger(LoggerTest.class);
    public static void main(String[] args) {
        logger.fatal("fatal");
        logger.trace("trace");
        logger.warn("warn");
        logger.error("error",new RuntimeException());

    }
}
