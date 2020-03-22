package com.ht;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;

import java.util.Date;
import java.util.HashSet;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author mbql
 * @date 2020/3/22 17:06
 */
public class HolidayCalendarTest {
    public static void main(String[] args) {
        try {
            SchedulerFactory schedFact = new StdSchedulerFactory();

            Scheduler sched = schedFact.getScheduler();
            Date date = new Date();
            sched.start();
            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("myJob", "group1")
                    .usingJobData("jobs","你好呀")
                    .usingJobData("floats",6.5f)
                    .build();
            HolidayCalendar cal = new HolidayCalendar();
            cal.addExcludedDate(date);
            // cal.addExcludedDate(someOtherDate);

            sched.addCalendar("myHolidays", cal, false,false);

            Trigger t = newTrigger()
                    .withIdentity("myTrigger")
                    .forJob("myJob")
                    .withSchedule(dailyAtHourAndMinute(17, 28)) // execute job daily at 9:30
                    .modifiedByCalendar("myHolidays") // but not on holidays
                    .build();

// .. schedule job with trigger

            Trigger t2 = newTrigger()
                    .withIdentity("myTrigger2")
                    .forJob("myJob2")
                    .withSchedule(dailyAtHourAndMinute(11, 30)) // execute job daily at 11:30
                    .modifiedByCalendar("myHolidays") // but not on holidays
                    .build();
            HashSet<Trigger> triggers = new HashSet<Trigger>();
            triggers.add(t);
            triggers.add(t2);
            // Tell quartz to schedule the job using our trigger
            sched.scheduleJob(job,triggers ,true);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
