package com.mmj.hello;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by MaMingJiang on 2016/4/18.
 */
public class SchedulerTest {

    /*

      核心接口： Scheduler
               Job
               JobDetail
               Trigger
               JobBuilder
               TriggerBuilder

     */

    public static void main(String[] args) {
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.start();
            //需要执行的具体任务
            JobDetail job = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("job1","group1")
                    .usingJobData("name","Brian")
                    .build();
//            job.getJobDataMap().put("name","Brian");

            //任务的执行规则   分为SimpleTrigger 和 CronTrigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1","group1")
                    .startNow()                                                //repeatForver一直持续，否则只调用一次
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(15).repeatForever())
                    .build();

            //绑定任务和调度规则
            scheduler.scheduleJob(job,trigger);

//            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
