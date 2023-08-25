package com.ruoyi.microgrid.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.microgrid.domain.Devices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.microgrid.domain.Terminals;
import com.ruoyi.microgrid.service.ITerminalsService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 终端表Controller
 * 
 * @author zxs
 * @date 2023-05-31
 */
@RestController
@RequestMapping("/terminals")
public class TerminalsController extends BaseController
{
    @Autowired
    private ITerminalsService terminalsService;

    /**
     * 查询终端表列表
     */
    @RequiresPermissions("microgrid:terminals:list")
    @GetMapping("/list")
    public TableDataInfo list(Terminals terminals)
    {
        startPage();
        List<Terminals> list = terminalsService.selectTerminalsList(terminals);
        return getDataTable(list);
    }

    @GetMapping("/listByType")
    public TableDataInfo listByType(String terminalNo)
    {
        List<Terminals> list = terminalsService.getTerminalList(terminalNo);
        return getDataTable(list);
    }

    /**
     * 导出终端表列表
     */
    @RequiresPermissions("microgrid:terminals:export")
    @Log(title = "终端表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Terminals terminals)
    {
        List<Terminals> list = terminalsService.selectTerminalsList(terminals);
        ExcelUtil<Terminals> util = new ExcelUtil<Terminals>(Terminals.class);
        util.exportExcel(response, list, "终端表数据");
    }

    /**
     * 获取终端表详细信息
     */
    @RequiresPermissions("microgrid:terminals:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(terminalsService.selectTerminalsById(id));
    }

    /**
     * 新增终端表
     */
    @RequiresPermissions("microgrid:terminals:add")
    @Log(title = "终端表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Terminals terminals)
    {
        return toAjax(terminalsService.insertTerminals(terminals));
    }

    /**
     * 修改终端表
     */
    @RequiresPermissions("microgrid:terminals:edit")
    @Log(title = "终端表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Terminals terminals)
    {
        return toAjax(terminalsService.updateTerminals(terminals));
    }

    /**
     * 删除终端表
     */
    @RequiresPermissions("microgrid:terminals:remove")
    @Log(title = "终端表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(terminalsService.deleteTerminalsByIds(ids));
    }
}
