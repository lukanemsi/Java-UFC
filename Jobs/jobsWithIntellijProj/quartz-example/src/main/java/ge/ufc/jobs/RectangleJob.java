package ge.ufc.jobs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ge.ufc.figures.Rectangle;
import ge.ufc.figures.Rectangles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.*;
import java.util.Random;


public class RectangleJob implements Job {
    Logger logger = LogManager.getLogger();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Random random = new Random();
        File file = new File("rectangles.json");
        if(!file.exists())
        {
            try {
                if(!file.createNewFile())
                    throw new IOException();
            } catch (IOException e) {
                logger.error("IO Exception");
                throw new RuntimeException(e);
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Rectangle r = new Rectangle(random.nextInt(Integer.MAX_VALUE-1)+1,random.nextInt(Integer.MAX_VALUE-1)+1);
        Rectangles rectangles = null;

        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String content = reader.lines().reduce("",(a,b) -> a+b);
            if(!content.isEmpty())
            {
                rectangles = gson.fromJson(content,Rectangles.class);

            }
            else
            {
                rectangles = new Rectangles();

            }
        } catch (IOException e)
        {
            logger.error("IO Exception");
            throw new RuntimeException(e);
        }
        rectangles.getRectangleList().add(r);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            writer.write(gson.toJson(rectangles));
            logger.debug("Json Rectangle added: " + r);
        } catch (IOException e) {
            logger.error("IO Exception");
            throw new RuntimeException(e);
        }
    }
}
