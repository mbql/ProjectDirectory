package com.ht.springbootquartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mbql
 * @date 2020/3/22 19:41
 */
public class DateTimeJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            JobKey key = jobExecutionContext.getJobDetail().getKey();
            String msg = jobExecutionContext.getJobDetail().getJobDataMap().getString("msg");
            System.out.println("current time :"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +"---"+key+ "---" + msg);
        }catch (SchedulingException se){
            se.printStackTrace();
        }
    }
}
