package com.github.aaric.quartz.api.impl;

import com.github.aaric.quartz.api.QuartzApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * JobController
 *
 * @author Aaric, created on 2020-10-14T11:38.
 * @version 0.2.0-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/api/quartz")
public class QuartzController implements QuartzApi {

    @Override
    @GetMapping("/create/{jobName}")
    public Map<String, String> create(@PathVariable String jobName) {
        log.info("jobName: {}", jobName);
        return response(jobName);
    }

    @Override
    @PutMapping("/pause/{jobName}")
    public Map<String, String> pause(@PathVariable String jobName) {
        log.info("jobName: {}", jobName);
        return response(jobName);
    }

    @Override
    @PutMapping("/resume/{jobName}")
    public Map<String, String> resume(@PathVariable String jobName) {
        log.info("jobName: {}", jobName);
        return response(jobName);
    }

    @Override
    @DeleteMapping("/remove/{jobName}")
    public Map<String, String> remove(@PathVariable String jobName) {
        log.info("jobName: {}", jobName);
        return response(jobName);
    }

    @Override
    @GetMapping("/exists/{jobName}")
    public Map<String, String> exists(@PathVariable String jobName) {
        log.info("jobName: {}", jobName);
        return response(jobName);
    }

    /**
     * 响应数据
     *
     * @param data 附加信息
     * @return
     */
    private Map<String, String> response(String data) {
        Map<String, String> dataMap = new HashMap<>(3);
        dataMap.put("code", "0000");
        dataMap.put("msg", "SUCCESS");
        dataMap.put("data", data);
        return dataMap;
    }
}
