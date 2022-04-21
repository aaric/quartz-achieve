package com.github.aaric.quartz.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.Instant;

/**
 * ClusterJob
 *
 * @author Aaric, created on 2020-10-13T14:30.
 * @version 0.2.0-SNAPSHOT
 */
@Slf4j
public class ClusterJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        log.info("ClusterJob, {} - {}. JobDataMap -> id: {}, name: {}", Instant.now().toEpochMilli(), Thread.currentThread().getName(),
                jobDataMap.getLong("id"), jobDataMap.getString("name"));
    }
}
