package com.ruoyi.microgrid.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.microgrid.tools.TimestampToDateUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * power_wind对象 power_wind
 * 
 * @author zxs
 * @date 2023-05-23
 */
public class PowerWind
{
    /** 主键 */
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
    private BigDecimal windCurrent;

    /** 电压 */
    @Excel(name = "电压")
    private BigDecimal windVoltage;

    /** 功率 */
    @Excel(name = "功率")
    private BigDecimal windPower;

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

    public BigDecimal getWindCurrent() {
        return windCurrent;
    }

    public void setWindCurrent(BigDecimal windCurrent) {
        this.windCurrent = windCurrent;
    }

    public BigDecimal getWindVoltage() {
        return windVoltage;
    }

    public void setWindVoltage(BigDecimal windVoltage) {
        this.windVoltage = windVoltage;
    }

    public BigDecimal getWindPower() {
        return windPower;
    }

    public void setWindPower(BigDecimal windPower) {
        this.windPower = windPower;
    }

    public PowerWind() {
    }

    public PowerWind(String terminalNo, Date timestamp, BigDecimal windCurrent, BigDecimal windVoltage, BigDecimal windPower) {
        this.terminalNo = terminalNo;
        this.timestamp = timestamp;
        this.windCurrent = windCurrent;
        this.windVoltage = windVoltage;
        this.windPower = windPower;
    }

    public PowerWind(Long id, String terminalNo, Date timestamp, BigDecimal windCurrent, BigDecimal windVoltage, BigDecimal windPower) {
        this.id = id;
        this.terminalNo = terminalNo;
        this.timestamp = timestamp;
        this.windCurrent = windCurrent;
        this.windVoltage = windVoltage;
        this.windPower = windPower;
    }

    @Override
    public String toString() {
        return "PowerWind{" +
                "id=" + id +
                ", terminalNo='" + terminalNo + '\'' +
                ", timestamp=" + timestamp +
                ", windCurrent=" + windCurrent +
                ", windVoltage=" + windVoltage +
                ", windPower=" + windPower +
                '}';
    }
}
