package ge.ufc.jobs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ge.ufc.figures.Circles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.StringJoiner;

import ge.ufc.figures.Circle;

public class CircleJob implements Job {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        Random random = new Random();
        File file = new File("circles.json");
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
        Circle c = new Circle(random.nextInt(Integer.MAX_VALUE-1)+1);
        Circles circles = null;

        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String content = reader.lines().reduce("",(a,b) -> a+b);
            if(!content.isEmpty())
            {
                circles = gson.fromJson(content,Circles.class);

            }
            else
            {
                circles = new Circles();
            }
        } catch (IOException e)
        {
            logger.error("IO Exception");
            throw new RuntimeException(e);
        }
        circles.getCircleList().add(c);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            writer.write(gson.toJson(circles));
            logger.debug("Json Circle added: " + c);
        } catch (IOException e) {
            logger.error("IO Exception");
            throw new RuntimeException(e);
        }
    }
}
