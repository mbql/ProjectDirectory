package com.ht;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author mbql
 * @date 2020/3/22 15:20
 */
public class QuartzTest {

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
            // Trigger the job to run now, and then every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    // .startAt(evenHourDate(null))
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(40)
                            .repeatForever())
                    // .endAt(dateOf(22,0,0))
                    .build();
            System.out.println(trigger.getKey());
            // Tell quartz to schedule the job using our trigger
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
