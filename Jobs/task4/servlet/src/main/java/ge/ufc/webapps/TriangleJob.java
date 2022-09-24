package ge.ufc.webapps;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TriangleJob implements Job {
    private static final Logger lgg = LogManager.getLogger(TriangleJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        Random random = new Random();
        int quantity = jobExecutionContext.getJobDetail().getJobDataMap().getInt("X");
        List<Triangle> randomTriangles = new ArrayList<>();
        while (quantity != 0)
        {
            quantity--;
            Triangle triangle = new Triangle
                    (random.nextInt(Integer.MAX_VALUE-1)+1,random.nextInt(Integer.MAX_VALUE-1)+1,random.nextInt(Integer.MAX_VALUE-1)+1);
            randomTriangles.add(triangle);
        }
        lgg.debug("in");
        try {
            JAXBContext context = JAXBContext.newInstance(Triangles.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Triangles triangles = (Triangles) unmarshaller.unmarshal(new File(jobExecutionContext.getJobDetail().getJobDataMap().getString("path")));
            triangles.getTriangles().addAll(randomTriangles);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(triangles, new FileWriter(new File(jobExecutionContext.getJobDetail().getJobDataMap().getString("path"))));
            lgg.debug("Triangle added");
        } catch (JAXBException | IOException e) {
            lgg.error("Error");
            throw new RuntimeException(e);
        }
    }
}
