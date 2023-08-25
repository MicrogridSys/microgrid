package com.ruoyi.microgrid.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.microgrid.tools.TimestampToDateUtils;

import java.math.BigDecimal;
import java.util.Date;


public class MainGrid {
    private Long id;

    /** 终端外键 */
    @Excel(name = "终端外键")
    private String terminalNo;

    /** 时间戳 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间戳", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonDeserialize(using = TimestampToDateUtils.class)
    private Date timestamp;

    /** 电流 */
    @Excel(name = "电流")
    private BigDecimal mgCurrent;

    /** 电压 */
    @Excel(name = "电压")
    private BigDecimal mgVoltage;

    /** 功率 */
    @Excel(name = "功率")
    private BigDecimal mgPower;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getMgCurrent() {
        return mgCurrent;
    }

    public void setMgCurrent(BigDecimal mgCurrent) {
        this.mgCurrent = mgCurrent;
    }

    public BigDecimal getMgVoltage() {
        return mgVoltage;
    }

    public void setMgVoltage(BigDecimal mgVoltage) {
        this.mgVoltage = mgVoltage;
    }

    public BigDecimal getMgPower() {
        return mgPower;
    }

    public void setMgPower(BigDecimal mgPower) {
        this.mgPower = mgPower;
    }

    public MainGrid() {
    }

    public MainGrid(String terminalNo, Date timestamp, BigDecimal mgCurrent, BigDecimal mgVoltage, BigDecimal mgPower) {
        this.terminalNo = terminalNo;
        this.timestamp = timestamp;
        this.mgCurrent = mgCurrent;
        this.mgVoltage = mgVoltage;
        this.mgPower = mgPower;
    }

    public MainGrid(Long id, String terminalNo, Date timestamp, BigDecimal mgCurrent, BigDecimal mgVoltage, BigDecimal mgPower) {
        this.id = id;
        this.terminalNo = terminalNo;
        this.timestamp = timestamp;
        this.mgCurrent = mgCurrent;
        this.mgVoltage = mgVoltage;
        this.mgPower = mgPower;
    }

    @Override
    public String toString() {
        return "MainGrid{" +
                "id=" + id +
                ", terminalNo='" + terminalNo + '\'' +
                ", timestamp=" + timestamp +
                ", mgCurrent=" + mgCurrent +
                ", mgVoltage=" + mgVoltage +
                ", mgPower=" + mgPower +
                '}';
    }
}
