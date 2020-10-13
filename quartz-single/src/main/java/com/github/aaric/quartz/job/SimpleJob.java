package com.github.aaric.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.Instant;

/**
 * SimpleJob
 *
 * @author Aaric, created on 2020-10-13T09:31.
 * @version 0.1.0-SNAPSHOT
 */
@Slf4j
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println("-----");
        log.info("Hello, {} - {}.", Instant.now().toEpochMilli(), Thread.currentThread().getName());
    }
}
