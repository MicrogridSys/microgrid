package com.ruoyi.microgrid.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.microgrid.tools.TimestampToDateUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * power_gen对象 power_gen
 *
 * @author zxs
 * @date 2023-05-19
 */
public class PowerGen {
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
    private BigDecimal genCurrent;

    /** 电压 */
    @Excel(name = "电压")
    private BigDecimal genVoltage;

    /** 功率 */
    @Excel(name = "功率")
    private BigDecimal genPower;

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

    public BigDecimal getGenCurrent() {
        return genCurrent;
    }

    public void setGenCurrent(BigDecimal genCurrent) {
        this.genCurrent = genCurrent;
    }

    public BigDecimal getGenVoltage() {
        return genVoltage;
    }

    public void setGenVoltage(BigDecimal genVoltage) {
        this.genVoltage = genVoltage;
    }

    public BigDecimal getGenPower() {
        return genPower;
    }

    public void setGenPower(BigDecimal genPower) {
        this.genPower = genPower;
    }

    public PowerGen() {
    }

    public PowerGen(String terminalNo, Date timestamp, BigDecimal genCurrent, BigDecimal genVoltage, BigDecimal genPower) {
        this.terminalNo = terminalNo;
        this.timestamp = timestamp;
        this.genCurrent = genCurrent;
        this.genVoltage = genVoltage;
        this.genPower = genPower;
    }

    public PowerGen(Long id, String terminalNo, Date timestamp, BigDecimal genCurrent, BigDecimal genVoltage, BigDecimal genPower) {
        this.id = id;
        this.terminalNo = terminalNo;
        this.timestamp = timestamp;
        this.genCurrent = genCurrent;
        this.genVoltage = genVoltage;
        this.genPower = genPower;
    }

    @Override
    public String toString() {
        return "PowerGen{" +
                "id=" + id +
                ", terminalNo='" + terminalNo + '\'' +
                ", timestamp=" + timestamp +
                ", genCurrent=" + genCurrent +
                ", genVoltage=" + genVoltage +
                ", genPower=" + genPower +
                '}';
    }
}
