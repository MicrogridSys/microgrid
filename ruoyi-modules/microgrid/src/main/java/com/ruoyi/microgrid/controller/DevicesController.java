package com.ruoyi.microgrid.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.microgrid.domain.Devices;
import com.ruoyi.microgrid.service.IDevicesService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 设备管理Controller
 * 
 * @author zxs
 * @date 2023-05-31
 */
@RestController
@RequestMapping("/devices")
public class DevicesController extends BaseController
{
    @Autowired
    private IDevicesService devicesService;

    /**
     * 查询设备管理列表
     */
    @RequiresPermissions("microgrid:devices:list")
    @GetMapping("/list")
    public TableDataInfo list(Devices devices)
    {
        startPage();
        List<Devices> list = devicesService.selectDevicesList(devices);
        return getDataTable(list);
    }

    @GetMapping("/listByType")
    public TableDataInfo listByType(String deviceNo)
    {
        List<Devices> list = devicesService.getDeviceList(deviceNo);
        return getDataTable(list);
    }


    /**
     * 导出设备管理列表
     */
    @RequiresPermissions("microgrid:devices:export")
    @Log(title = "设备管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Devices devices)
    {
        List<Devices> list = devicesService.selectDevicesList(devices);
        ExcelUtil<Devices> util = new ExcelUtil<Devices>(Devices.class);
        util.exportExcel(response, list, "设备管理数据");
    }

    /**
     * 获取设备管理详细信息
     */
    @RequiresPermissions("microgrid:devices:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(devicesService.selectDevicesById(id));
    }

    /**
     * 新增设备管理
     */
    @RequiresPermissions("microgrid:devices:add")
    @Log(title = "设备管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Devices devices)
    {
        return toAjax(devicesService.insertDevices(devices));
    }

    /**
     * 修改设备管理
     */
    @RequiresPermissions("microgrid:devices:edit")
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Devices devices)
    {
        System.out.println(devices);
        return toAjax(devicesService.updateDevices(devices));
    }

    /**
     * 删除设备管理
     */
    @RequiresPermissions("microgrid:devices:remove")
    @Log(title = "设备管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(devicesService.deleteDevicesByIds(ids));
    }
}
