package com.sample.sfms.schedule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NotifyUserTrigger {
    private static Logger logger = Logger.getLogger(NotifyUserTrigger.class.getName());

    public static void notifyUser() {
        JobDetail job = JobBuilder.newJob(NotifyUserJob.class)
                .withIdentity("notifyUserJob", "notifyUserGroup").build();

        // Trigger the job to run on the next round minute
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("notifyUserTrigger", "notifyUserGroup")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInMinutes(1).repeatForever())
                .build();

        // schedule it
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.log(Level.FINE, e.toString());
        }
    }
}
