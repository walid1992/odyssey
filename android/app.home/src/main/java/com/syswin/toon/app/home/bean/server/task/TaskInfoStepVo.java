package com.syswin.toon.app.home.bean.server.task;

import com.syswin.toon.app.home.bean.server.annotation.ApiModelProperty;

import java.util.Date;

/**
 * @author taoming
 * @since 2016/9/10
 */
public class TaskInfoStepVo {

    @ApiModelProperty(value = "任务id")
    private int taskId; //任务id

    @ApiModelProperty(value = "任务步骤内容")
    private String content; //任务步骤内容

    @ApiModelProperty(value = "任务步骤图片")
    private String picJson; //任务步骤图片

    @ApiModelProperty(value = "任务步骤序号")
    private int stepNum; //任务步骤序号

    @ApiModelProperty(value = "创建时间")
    private Date createdTime; //创建时间

    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime; //修改时间

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicJson() {
        return picJson;
    }

    public void setPicJson(String picJson) {
        this.picJson = picJson;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
