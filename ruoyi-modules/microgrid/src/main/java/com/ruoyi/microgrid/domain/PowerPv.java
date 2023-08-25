package com.ruoyi.microgrid.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.microgrid.tools.TimestampToDateUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * power_pv对象 power_pv
 * 
 * @author zxs
 * @date 2023-05-23
 */
public class PowerPv
{
    /** 光伏 */
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
    private BigDecimal pvCurrent;

    /** 电压 */
    @Excel(name = "电压")
    private BigDecimal pvVoltage;

    /** 功率 */
    @Excel(name = "功率")
    private BigDecimal pvPower;

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

    public BigDecimal getPvCurrent() {
        return pvCurrent;
    }

    public void setPvCurrent(BigDecimal pvCurrent) {
        this.pvCurrent = pvCurrent;
    }

    public BigDecimal getPvVoltage() {
        return pvVoltage;
    }

    public void setPvVoltage(BigDecimal pvVoltage) {
        this.pvVoltage = pvVoltage;
    }

    public BigDecimal getPvPower() {
        return pvPower;
    }

    public void setPvPower(BigDecimal pvPower) {
        this.pvPower = pvPower;
    }

    public PowerPv() {
    }

    public PowerPv(String terminalNo, Date timestamp, BigDecimal pvCurrent, BigDecimal pvVoltage, BigDecimal pvPower) {
        this.terminalNo = terminalNo;
        this.timestamp = timestamp;
        this.pvCurrent = pvCurrent;
        this.pvVoltage = pvVoltage;
        this.pvPower = pvPower;
    }

    public PowerPv(Long id, String terminalNo, Date timestamp, BigDecimal pvCurrent, BigDecimal pvVoltage, BigDecimal pvPower) {
        this.id = id;
        this.terminalNo = terminalNo;
        this.timestamp = timestamp;
        this.pvCurrent = pvCurrent;
        this.pvVoltage = pvVoltage;
        this.pvPower = pvPower;
    }

    @Override
    public String toString() {
        return "PowerPv{" +
                "id=" + id +
                ", terminalNo='" + terminalNo + '\'' +
                ", timestamp=" + timestamp +
                ", pvCurrent=" + pvCurrent +
                ", pvVoltage=" + pvVoltage +
                ", pvPower=" + pvPower +
                '}';
    }
}
