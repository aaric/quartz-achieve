package com.github.aaric.quartz.runner;

import com.github.aaric.quartz.quartz.job.ClusterJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 初始化Quartz
 *
 * @author Aaric, created on 2020-10-13T14:33.
 * @version 0.2.0-SNAPSHOT
 */
@Order(1)
@Component
public class QuartzRunner implements CommandLineRunner {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(String... args) throws Exception {
        JobDetail jobDetail = JobBuilder.newJob(ClusterJob.class)
                .withDescription("Cluster Job")
                .withIdentity("defaultJob", Scheduler.DEFAULT_GROUP)
                .usingJobData("id", 1L)
                .usingJobData("name", "hello world")
                .requestRecovery()
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription("Cluster Job Trigger")
                .withIdentity("defaultTrigger", Scheduler.DEFAULT_GROUP)
                .startAt(new Date())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        JobKey jobKey = JobKey.jobKey("defaultJob", Scheduler.DEFAULT_GROUP);
        TriggerKey triggerKey = TriggerKey.triggerKey("defaultTrigger", Scheduler.DEFAULT_GROUP);
        if (!(scheduler.checkExists(jobKey) || scheduler.checkExists(triggerKey))) {
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }
}
