package com.github.aaric.quartz.runner;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 初始化Quartz
 *
 * @author Aaric, created on 2020-10-13T13:47.
 * @version 0.1.0-SNAPSHOT
 */
@Order(1)
@Component
public class QuartzRunner implements CommandLineRunner {

    @Autowired
    private JobDetail jobDetail;

    @Autowired
    private Trigger jobTrigger;

    @Override
    public void run(String... args) throws Exception {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();

        scheduler.scheduleJob(jobDetail, jobTrigger);
    }
}
