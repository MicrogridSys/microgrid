package com.ruoyi.microgrid.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.microgrid.tools.TimestampToDateUtils;

import java.math.BigDecimal;
import java.util.Date;

public class PowerEnergy {
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
    private BigDecimal eneCurrent;

    /** 电压 */
    @Excel(name = "电压")
    private BigDecimal eneVoltage;

    /** 功率 */
    @Excel(name = "功率")
    private BigDecimal enePower;

    /** 功率 */
    @Excel(name = "容量")
    private BigDecimal eneContain;

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

    public BigDecimal getEneCurrent() {
        return eneCurrent;
    }

    public void setEneCurrent(BigDecimal eneCurrent) {
        this.eneCurrent = eneCurrent;
    }

    public BigDecimal getEneVoltage() {
        return eneVoltage;
    }

    public void setEneVoltage(BigDecimal eneVoltage) {
        this.eneVoltage = eneVoltage;
    }

    public BigDecimal getEnePower() {
        return enePower;
    }

    public void setEnePower(BigDecimal enePower) {
        this.enePower = enePower;
    }

    public BigDecimal getEneContain() {
        return eneContain;
    }

    public void setEneContain(BigDecimal eneContain) {
        this.eneContain = eneContain;
    }

    public PowerEnergy() {
    }

    public PowerEnergy(String terminalNo, Date timestamp, BigDecimal eneCurrent, BigDecimal eneVoltage, BigDecimal enePower, BigDecimal eneContain) {
        this.terminalNo = terminalNo;
        this.timestamp = timestamp;
        this.eneCurrent = eneCurrent;
        this.eneVoltage = eneVoltage;
        this.enePower = enePower;
        this.eneContain = eneContain;
    }

    public PowerEnergy(Long id, String terminalNo, Date timestamp, BigDecimal eneCurrent, BigDecimal eneVoltage, BigDecimal enePower, BigDecimal eneContain) {
        this.id = id;
        this.terminalNo = terminalNo;
        this.timestamp = timestamp;
        this.eneCurrent = eneCurrent;
        this.eneVoltage = eneVoltage;
        this.enePower = enePower;
        this.eneContain = eneContain;
    }

    @Override
    public String toString() {
        return "PowerEnergy{" +
                "id=" + id +
                ", terminalNo='" + terminalNo + '\'' +
                ", timestamp=" + timestamp +
                ", eneCurrent=" + eneCurrent +
                ", eneVoltage=" + eneVoltage +
                ", enePower=" + enePower +
                ", eneContain=" + eneContain +
                '}';
    }
}
