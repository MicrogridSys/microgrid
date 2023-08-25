package com.ruoyi.microgrid.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.microgrid.mapper.DevicesMapper;
import com.ruoyi.microgrid.domain.Devices;
import com.ruoyi.microgrid.service.IDevicesService;

/**
 * 设备管理Service业务层处理
 * 
 * @author zxs
 * @date 2023-05-31
 */
@Service
public class DevicesServiceImpl implements IDevicesService 
{
    @Autowired
    private DevicesMapper devicesMapper;


    @Override
    public List<Devices> getDeviceList(String deviceNo) {
        return devicesMapper.getDeviceList(deviceNo+"%");
    }

    /**
     * 查询设备管理
     * 
     * @param id 设备管理主键
     * @return 设备管理
     */
    @Override
    public Devices selectDevicesById(Long id)
    {
        return devicesMapper.selectDevicesById(id);
    }

    /**
     * 查询设备管理列表
     * 
     * @param devices 设备管理
     * @return 设备管理
     */
    @Override
    public List<Devices> selectDevicesList(Devices devices)
    {
        return devicesMapper.selectDevicesList(devices);
    }

    /**
     * 新增设备管理
     * 
     * @param devices 设备管理
     * @return 结果
     */
    @Override
    public int insertDevices(Devices devices)
    {
        return devicesMapper.insertDevices(devices);
    }

    /**
     * 修改设备管理
     * 
     * @param devices 设备管理
     * @return 结果
     */
    @Override
    public int updateDevices(Devices devices)
    {
        return devicesMapper.updateDevices(devices);
    }

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的设备管理主键
     * @return 结果
     */
    @Override
    public int deleteDevicesByIds(Long[] ids)
    {
        return devicesMapper.deleteDevicesByIds(ids);
    }

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理主键
     * @return 结果
     */
    @Override
    public int deleteDevicesById(Long id)
    {
        return devicesMapper.deleteDevicesById(id);
    }
}
