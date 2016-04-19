package com.mmj.hello;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by MaMingJiang on 2016/4/18.
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        String name = (String)context.getJobDetail().getJobDataMap().get("name");
        System.out.println("hello," + name);
    }
}
