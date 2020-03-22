package com.ht;

import org.quartz.*;

import java.util.ArrayList;

/**
 * @author mbql
 * @date 2020/3/22 15:33
 */
public class HelloJob implements Job {

    String jobs;
    float floats;
    ArrayList state;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        // String jobs = jobDataMap.getString("jobs");
        // float aFloat = jobDataMap.getFloat("float");
        System.err.println("Instance " + key + " of DumbJob says: " + jobs + ", and val is: " + floats);
        // JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        // state.add(new Date());
        // System.err.println("Instance " + key + " of DumbJob says: " + jobs + ", and val is: " + floats);
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public void setFloats(float floats) {
        this.floats = floats;
    }

    public void setState(ArrayList state) {
        this.state = state;
    }
}
