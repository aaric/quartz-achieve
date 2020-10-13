package com.github.aaric.quartz.runner;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 初始化Quartz
 *
 * @author Aaric, created on 2020-10-13T14:33.
 * @version 0.2.0-SNAPSHOT
 */
@Order(1)
@Component
public class QuartzRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        SchedulerFactory factory = new StdSchedulerFactory("quartz.properties");
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
    }
}
