package com.osmartian.small.app.home.bean.server.agent;

import java.math.BigDecimal;

/**
 * Author   : walid
 * Data     : 2016-09-08  21:04
 * Describe :
 */

public class TaskHeaderVo {

    private int taskAmount;
    private BigDecimal totalCommission;

    public TaskHeaderVo() {
    }

    public TaskHeaderVo(int count, BigDecimal price) {
        this.taskAmount = count;
        this.totalCommission = price;
    }

    public int getTaskAmount() {
        return taskAmount;
    }

    public void setTaskAmount(int taskAmount) {
        this.taskAmount = taskAmount;
    }

    public BigDecimal getTotalCommission() {
        return totalCommission;
    }

    public void setTotalCommission(BigDecimal totalCommission) {
        this.totalCommission = totalCommission;
    }
}
