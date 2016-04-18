package com.mmj.hello;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by MaMingJiang on 2016/4/18.
 */
public class SchedulerTest {
    public static void main(String[] args) {
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.start();

            JobDetail job = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("job1","group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1","group1")
                    .startNow()                                                //repeatForver一直持续，否则只调用一次
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(15).repeatForever())
                    .build();

            scheduler.scheduleJob(job,trigger);

//            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
