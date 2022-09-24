package ge.ufc;

import ge.ufc.jobs.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class App {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();

            // onStart triggers
            JobDetail circleJob = JobBuilder.newJob(CircleJob.class).withIdentity("circleJob","groupCircle").build();
            Trigger circleTrigger = TriggerBuilder.newTrigger().withIdentity("circleTrigger","groupCircle").startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(4)).build();

            JobDetail triangleJob = JobBuilder.newJob(TriangleJob.class).withIdentity("triangleJob","groupTriangle").build();
            Trigger triangleTrigger = TriggerBuilder.newTrigger().withIdentity("triangleTrigger","groupTriangle").startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(7).withRepeatCount(5)).build();

            JobDetail rectangleJob = JobBuilder.newJob(RectangleJob.class).withIdentity("rectangleJob","groupRectangle").build();
            Trigger rectangleTrigger = TriggerBuilder.newTrigger().withIdentity("rectangleTrigger","groupRectangle").startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(6).withRepeatCount(3)).build();
            scheduler.scheduleJob(circleJob,circleTrigger);
            scheduler.scheduleJob(triangleJob,triangleTrigger);
            scheduler.scheduleJob(rectangleJob,rectangleTrigger);
            scheduler.start();

            try{
                // scheduler.shutdown(true); ratomgac ar moushaobda
                Thread.sleep(40_000);
                scheduler.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }


    }
}
