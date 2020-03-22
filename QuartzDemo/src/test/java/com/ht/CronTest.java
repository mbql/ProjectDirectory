package com.ht;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author mbql
 * @date 2020/3/22 18:02
 */
public class CronTest {

    public static void main(String[] args) {
        try {
            SchedulerFactory schedFact = new StdSchedulerFactory();

            Scheduler sched = schedFact.getScheduler();

            sched.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("myJob", "group1")
                    .usingJobData("jobs","你好呀")
                    .usingJobData("floats",6.5f)
                    .build();
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .withSchedule(cronSchedule("0 0/2 8-17 * * ?"))
                    .build();
            System.out.println(trigger.getKey());
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

}
