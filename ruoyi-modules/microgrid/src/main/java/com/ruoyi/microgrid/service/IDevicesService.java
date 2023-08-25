package com.ruoyi.microgrid.service;

import java.util.List;
import com.ruoyi.microgrid.domain.Devices;

/**
 * 设备管理Service接口
 * 
 * @author zxs
 * @date 2023-05-31
 */
public interface IDevicesService 
{

    public List<Devices> getDeviceList(String deviceNo);
    /**
     * 查询设备管理
     * 
     * @param id 设备管理主键
     * @return 设备管理
     */
    public Devices selectDevicesById(Long id);

    /**
     * 查询设备管理列表
     * 
     * @param devices 设备管理
     * @return 设备管理集合
     */
    public List<Devices> selectDevicesList(Devices devices);

    /**
     * 新增设备管理
     * 
     * @param devices 设备管理
     * @return 结果
     */
    public int insertDevices(Devices devices);

    /**
     * 修改设备管理
     * 
     * @param devices 设备管理
     * @return 结果
     */
    public int updateDevices(Devices devices);

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的设备管理主键集合
     * @return 结果
     */
    public int deleteDevicesByIds(Long[] ids);

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理主键
     * @return 结果
     */
    public int deleteDevicesById(Long id);
}
