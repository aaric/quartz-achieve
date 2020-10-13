package com.github.aaric.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * SimpleJobTests
 *
 * @author Aaric, created on 2020-10-13T09:44.
 * @version 0.1.0-SNAPSHOT
 */
@Slf4j
public class SimpleJobTests {

    @Test
    @Disabled
    public void testExecute() throws Exception {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                .withDescription("Simple Job")
                .withIdentity("defaultJob", "defaultGroup")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription("Simple Job Trigger")
                .withIdentity("defaultTrigger", "defaultGroup")
                .startAt(new Date())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();

        log.info("job start...");

        TimeUnit.SECONDS.sleep(10);

        log.info("job end.");
    }
}
