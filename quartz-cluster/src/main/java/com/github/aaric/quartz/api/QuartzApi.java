package com.github.aaric.quartz.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

/**
 * JobApi
 *
 * @author Aaric, created on 2020-10-14T11:37.
 * @version 0.2.0-SNAPSHOT
 */
@Api(tags = "Quartz调度模块API")
public interface QuartzApi {

    /**
     * 创建任务
     *
     * @param jobName 任务名称
     * @return
     */
    @ApiOperation("创建任务")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "jobName", type = "path", example = "clusterJob")
    )
    Map<String, String> create(String jobName);

    /**
     * 暂停任务
     *
     * @param jobName 任务名称
     * @return
     */
    @ApiOperation("暂停任务")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "jobName", type = "path", example = "clusterJob")
    )
    Map<String, String> pause(String jobName);

    /**
     * 恢复任务
     *
     * @param jobName 任务名称
     * @return
     */
    @ApiOperation("恢复任务")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "jobName", type = "path", example = "clusterJob")
    )
    Map<String, String> resume(String jobName);

    /**
     * 删除任务
     *
     * @param jobName 任务名称
     * @return
     */
    @ApiOperation("删除任务")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "jobName", type = "path", example = "clusterJob")
    )
    Map<String, String> remove(String jobName);

    /**
     * 是否存在任务
     *
     * @param jobName 任务名称
     * @return
     */
    @ApiOperation("是否存在任务")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "jobName", type = "path", example = "clusterJob")
    )
    Map<String, String> exists(String jobName);
}
