package com.github.aaric.quartz.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.time.Instant;

/**
 * SimpleJob
 *
 * @author Aaric, created on 2020-10-13T09:31.
 * @version 0.1.0-SNAPSHOT
 */
@Slf4j
@DisallowConcurrentExecution
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        log.info("SimpleJob, {} - {}. JobDataMap -> id: {}, name: {}", Instant.now().toEpochMilli(), Thread.currentThread().getName(),
                jobDataMap.getLong("id"), jobDataMap.getString("name"));
    }
}
