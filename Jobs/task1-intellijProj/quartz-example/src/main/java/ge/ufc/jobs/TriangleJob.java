package ge.ufc.jobs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ge.ufc.figures.Triangle;
import ge.ufc.figures.Triangles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.*;
import java.util.Random;


public class TriangleJob implements Job {
    Logger logger = LogManager.getLogger();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Random random = new Random();
        File file = new File("triangles.json");
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
        Triangle t = new Triangle
                (random.nextInt(Integer.MAX_VALUE-1)+1,random.nextInt(Integer.MAX_VALUE-1)+1,random.nextInt(Integer.MAX_VALUE-1)+1);
        Triangles triangles = null;

        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String content = reader.lines().reduce("",(a,b) -> a+b);
            if(!content.isEmpty())
            {
                triangles = gson.fromJson(content,Triangles.class);

            }
            else
            {
                triangles = new Triangles();
            }
        } catch (IOException e)
        {
            logger.error("IO Exception");
            throw new RuntimeException(e);
        }
        triangles.getTriangleList().add(t);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            writer.write(gson.toJson(triangles));
            logger.debug("Json Triangle added: " + t);
        } catch (IOException e) {
            logger.error("IO Exception");
            throw new RuntimeException(e);
        }
    }
}
