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
 * 终端表对象 terminals
 * 
 * @author zxs
 * @date 2023-05-31
 */
public class Terminals extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识终端的ID */
    private Long id;

    /** 终端编号 */
    @Excel(name = "终端编号")
    private String terminalNo;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String deviceNo;

    /** 终端状态 */
    @Excel(name = "终端状态")
    private Long terminalStatus;

    /** 终端位置信息 */
    @Excel(name = "终端位置信息")
    private String location;

    /** 安装日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "安装日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonDeserialize(using = TimestampToDateUtils.class)
    private Date installationDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTerminalNo(String terminalNo) 
    {
        this.terminalNo = terminalNo;
    }

    public String getTerminalNo() 
    {
        return terminalNo;
    }
    public void setDeviceNo(String deviceNo) 
    {
        this.deviceNo = deviceNo;
    }

    public String getDeviceNo() 
    {
        return deviceNo;
    }
    public void setTerminalStatus(Long terminalStatus) 
    {
        this.terminalStatus = terminalStatus;
    }

    public Long getTerminalStatus() 
    {
        return terminalStatus;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setInstallationDate(Date installationDate) 
    {
        this.installationDate = installationDate;
    }

    public Date getInstallationDate() 
    {
        return installationDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("terminalNo", getTerminalNo())
            .append("deviceNo", getDeviceNo())
            .append("terminalStatus", getTerminalStatus())
            .append("location", getLocation())
            .append("installationDate", getInstallationDate())
            .toString();
    }
}
