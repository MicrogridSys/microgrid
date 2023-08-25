package com.ruoyi.microgrid.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.microgrid.tools.TimestampToDateUtils;

import java.math.BigDecimal;
import java.util.Date;

public class Loads {
    private Long id;

    /** 时间戳 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间戳", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonDeserialize(using = TimestampToDateUtils.class)
    private Date timestamp;

    /** 负载 */
    @Excel(name = "负载")
    private BigDecimal loadsPower;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getLoadsPower() {
        return loadsPower;
    }

    public void setLoadsPower(BigDecimal loadsPower) {
        this.loadsPower = loadsPower;
    }
    public Loads() {
    }

    public Loads(Date timestamp, BigDecimal loadsPower) {
        this.timestamp = timestamp;
        this.loadsPower = loadsPower;
    }

    public Loads(Long id, Date timestamp, BigDecimal loadsPower) {
        this.id = id;
        this.timestamp = timestamp;
        this.loadsPower = loadsPower;
    }

    @Override
    public String toString() {
        return "Loads{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", loadsPower=" + loadsPower +
                '}';
    }
}
