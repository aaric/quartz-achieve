package com.github.aaric.quartz.config;

import com.github.aaric.quartz.quartz.job.SimpleJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * Quartz配置
 *
 * @author Aaric, created on 2020-10-13T13:45.
 * @version 0.1.0-SNAPSHOT
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(SimpleJob.class)
                .withDescription("Simple Job")
                .withIdentity("defaultJob", Scheduler.DEFAULT_GROUP)
                .usingJobData("id", 1L)
                .usingJobData("name", "hello world")
                .requestRecovery()
                .build();
    }

    @Bean
    public Trigger jobTrigger() {
        return TriggerBuilder.newTrigger()
                .withDescription("Simple Job Trigger")
                .withIdentity("defaultTrigger", Scheduler.DEFAULT_GROUP)
                .startAt(new Date())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
    }
}
