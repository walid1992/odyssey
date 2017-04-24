package com.syswin.toon.app.home.bean.server.task;

import com.syswin.toon.app.home.bean.server.annotation.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author taoming
 * @since 2016/9/10
 */
public class TaskInfoVo implements Serializable {

    @ApiModelProperty(value = "任务id")
    private Integer id;

    @ApiModelProperty(value = "任务名称")
    private String taskTitle; //任务名称

    @ApiModelProperty(value = "截止时间")
    private Date endTime; //截止时间

    @ApiModelProperty(value = "佣金")
    private BigDecimal commission; //佣金

    @ApiModelProperty(value = "剩余次数")
    private int residueAmount; //剩余次数

    @ApiModelProperty(value = "任务总数")
    private int taskAmount; //任务总数

    @ApiModelProperty(value = "审核时间")
    private int auditInfo; //审核信息

    @ApiModelProperty(value = "任务限制类型")
    private int taskConstraint; //任务限制类型

    @ApiModelProperty(value = "任务限制描述")
    private String taskConstraintDesc; //任务限制描述

    @ApiModelProperty(value = "任务介绍")
    private String taskInfo; //任务介绍

    @ApiModelProperty(value = "任务有效期,用户领取任务至用户提交任务的时间，以分钟为单位")
    private int validTime; //任务有效期,用户领取任务至用户提交任务的时间，以分钟为单位

    @ApiModelProperty(value = "是否能领取")
    private boolean canApply; //能否被申请

    private Date createdTime;
    private Date modifiedTime;

    @ApiModelProperty(value = "步骤信息")
    private List<TaskInfoStepVo> taskInfoSteps; //步骤信息

    @ApiModelProperty(value = "任务状态")
    private int taskStatus; //任务状态

    @ApiModelProperty(value = "进程中的任务信息")
    private UserTaskItemModel userTaskItem; //如果用户有进程中的该任务，则根据进程任务进行自定义显示

    public UserTaskItemModel getUserTaskItem() {
        return userTaskItem;
    }

    public void setUserTaskItem(UserTaskItemModel userTaskItem) {
        this.userTaskItem = userTaskItem;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public List<TaskInfoStepVo> getTaskInfoSteps() {
        return taskInfoSteps;
    }

    public void setTaskInfoSteps(List<TaskInfoStepVo> taskInfoSteps) {
        this.taskInfoSteps = taskInfoSteps;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public int getTaskAmount() {
        return taskAmount;
    }

    public void setTaskAmount(int taskAmount) {
        this.taskAmount = taskAmount;
    }

    public int getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(int auditInfo) {
        this.auditInfo = auditInfo;
    }

    public int getTaskConstraint() {
        return taskConstraint;
    }

    public void setTaskConstraint(int taskConstraint) {
        this.taskConstraint = taskConstraint;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    public int getValidTime() {
        return validTime;
    }

    public void setValidTime(int validTime) {
        this.validTime = validTime;
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

    public int getResidueAmount() {
        return residueAmount;
    }

    public void setResidueAmount(int residueAmount) {
        this.residueAmount = residueAmount;
    }

    public String getTaskConstraintDesc() {
        return taskConstraintDesc;
    }

    public void setTaskConstraintDesc(String taskConstraintDesc) {
        this.taskConstraintDesc = taskConstraintDesc;
    }

    public boolean getCanApply() {
        return canApply;
    }

    public void setCanApply(boolean canApply) {
        this.canApply = canApply;
    }

}
