package com.osmartian.small.app.home.bean.server.task;

import com.osmartian.small.app.home.bean.server.annotation.ApiModelProperty;

import java.util.Date;

/**
 * @author taoming
 * @since 2016/9/10
 */
public class UserTaskItemModel {

    @ApiModelProperty(value = "用户")
    private int id;

    @ApiModelProperty(value = "用户id")
    private int uid; //用户id

    @ApiModelProperty(value = "任务id")
    private int taskId; //任务id

    @ApiModelProperty(value = "任务详情")
    private TaskInfoVo taskInfo;

    @ApiModelProperty(value = "任务失效时间")
    private Date disabledTime; //任务失效时间

    @ApiModelProperty(value = "任务进行状态（0=进行中，1=审核中，2=已完成，-1=已过期，-2=未审核通过）")
    private int status; //任务进行状态（0=进行中，1=审核中，2=已完成，-1=已过期，-2=未审核通过）

    @ApiModelProperty(value = "任务审核时间")
    private Date auditTime; //任务审核时间

    @ApiModelProperty(value = "任务内容")
    private String content; //任务内容

    @ApiModelProperty(value = "任务截图")
    private String picJson; //任务截图

    @ApiModelProperty(value = "创建时间")
    private Date createdTime; //创建时间

    @ApiModelProperty(value = "任务提交时间")
    private Date taskPostTime; //任务提交时间

    @ApiModelProperty(value = "如果是进行中的任务,对此字段进行倒计时")
    private long disabledTimeCountDown;//如果是进行中的任务

    @ApiModelProperty(value = "如果是审核中的任务,对此字段进行倒计时")
    private long auditTimeCountDown;//如果是审核中的任务

    public long getDisabledTimeCountDown() {
        return disabledTimeCountDown;
    }

    public void setDisabledTimeCountDown(long disabledTimeCountDown) {
        this.disabledTimeCountDown = disabledTimeCountDown;
    }

    public long getAuditTimeCountDown() {
        return auditTimeCountDown;
    }

    public void setAuditTimeCountDown(long auditTimeCountDown) {
        this.auditTimeCountDown = auditTimeCountDown;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskInfoVo getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(TaskInfoVo taskInfo) {
        this.taskInfo = taskInfo;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Date getDisabledTime() {
        return disabledTime;
    }

    public void setDisabledTime(Date disabledTime) {
        this.disabledTime = disabledTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getTaskPostTime() {
        return taskPostTime;
    }

    public void setTaskPostTime(Date taskPostTime) {
        this.taskPostTime = taskPostTime;
    }
}
