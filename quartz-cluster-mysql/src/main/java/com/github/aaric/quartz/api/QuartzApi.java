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
     * @throws Exception
     */
    @ApiOperation("创建任务")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "jobName", type = "path", example = "clusterJob")
    )
    Map<String, Object> create(String jobName) throws Exception;

    /**
     * 暂停任务
     *
     * @param jobName 任务名称
     * @return
     * @throws Exception
     */
    @ApiOperation("暂停任务")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "jobName", type = "path", example = "clusterJob")
    )
    Map<String, Object> pause(String jobName) throws Exception;

    /**
     * 恢复任务
     *
     * @param jobName 任务名称
     * @return
     * @throws Exception
     */
    @ApiOperation("恢复任务")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "jobName", type = "path", example = "clusterJob")
    )
    Map<String, Object> resume(String jobName) throws Exception;

    /**
     * 删除任务
     *
     * @param jobName 任务名称
     * @return
     * @throws Exception
     */
    @ApiOperation("删除任务")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "jobName", type = "path", example = "clusterJob")
    )
    Map<String, Object> remove(String jobName) throws Exception;

    /**
     * 是否存在任务
     *
     * @param jobName 任务名称
     * @return
     * @throws Exception
     */
    @ApiOperation("是否存在任务")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "jobName", type = "path", example = "clusterJob")
    )
    Map<String, Object> exists(String jobName) throws Exception;
}
