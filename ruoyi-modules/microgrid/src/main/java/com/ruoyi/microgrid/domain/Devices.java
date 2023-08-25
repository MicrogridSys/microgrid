package com.ruoyi.microgrid.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.microgrid.tools.TimestampToDateUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * devices对象 devices
 * 
 * @author zxs
 * @date 2023-05-31
 */
public class Devices
{
    /** 自增ID */
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String deviceNo;

    /** 设备状态 */
    @Excel(name = "设备状态")
    private Long deviceStatus;

    /** 设备所在的位置信息 */
    @Excel(name = "设备所在的位置信息")
    private String location;

    /** 设备安装的日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "设备安装的日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonDeserialize(using = TimestampToDateUtils.class)
    private Date installationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Long getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Long deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public Devices() {
    }

    public Devices(String deviceNo, Long deviceStatus, String location, Date installationDate) {
        this.deviceNo = deviceNo;
        this.deviceStatus = deviceStatus;
        this.location = location;
        this.installationDate = installationDate;
    }

    public Devices(Long id, String deviceNo, Long deviceStatus, String location, Date installationDate) {
        this.id = id;
        this.deviceNo = deviceNo;
        this.deviceStatus = deviceStatus;
        this.location = location;
        this.installationDate = installationDate;
    }

    @Override
    public String toString() {
        return "Devices{" +
                "id=" + id +
                ", deviceNo='" + deviceNo + '\'' +
                ", deviceStatus=" + deviceStatus +
                ", location='" + location + '\'' +
                ", installationDate=" + installationDate +
                '}';
    }
}
