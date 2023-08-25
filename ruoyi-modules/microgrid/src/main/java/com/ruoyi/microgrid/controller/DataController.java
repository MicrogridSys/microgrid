package com.ruoyi.microgrid.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.microgrid.domain.*;
import com.ruoyi.microgrid.service.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static com.ruoyi.common.core.utils.PageUtils.startPage;

@RestController
@RequestMapping("/data")
public class DataController extends BaseController {
    @Autowired
    private DataServiceImpl dataService;

    /**
     * 获取gen更新的数据集合
     * @return powerGen集合
     */
    @GetMapping("/powergens")
    public List<PowerGen> getPowerGenData() {
        return dataService.getRecentPowerGenData();
    }

    /**
     * 获取pv更新的数据集合
     * @return powerPv集合
     */
    @GetMapping("/powerpvs")
    public List<PowerPv> getPowerPvData() {
        return dataService.getRecentPowerPvData();
    }

    /**
     * 获取wind更新的数据集合
     * @return powerWind集合
     */
    @GetMapping("/powerwinds")
    public List<PowerWind> getPowerWindData() {
        return dataService.getRecentPowerWindData();
    }

    @GetMapping("/powerenergys")
    public List<PowerEnergy> getPowerEnergyData(){
        return dataService.getRecentPowerEnergyData();
    }

    @GetMapping("/loads")
    public List<Loads> getLoadsData(){
        return dataService.getRecentLoadsData();
    }

    @GetMapping("/maingrid")
    public List<MainGrid> getMainGridData(){
        return dataService.getRecentMainGridData();
    }

    @GetMapping("/powerpvtotal")
    public BigDecimal getTotalPowerPvData(){
        return dataService.getTotalPowerPvData();
    }

    @GetMapping("/powerwindtotal")
    public BigDecimal getTotalPowerWindData(){
        return dataService.getTotalPowerWindData();
    }

    @GetMapping("/listDataByType")
    public TableDataInfo getTotalPowerWindData(int pageNum, int pageSize, String terminalNo){
        // 计算分页的起始位置
        int start = (pageNum - 1) * pageSize;
        if ("gen".equals(terminalNo)){
            List<?> genData = dataService.getGenData(start, pageSize);
            return getDataTable(genData,dataService.getTotal());
        } else if ("wind".equals(terminalNo)) {
            return getDataTable(dataService.getWindData());
        }else if ("pv".equals(terminalNo)){
            return getDataTable(dataService.getPvData());
        } else if ("energy".equals(terminalNo)) {
            return getDataTable(dataService.getEnergyData());
        } else if ("mg".equals(terminalNo)) {
            return getDataTable(dataService.getMainGridData());
        } else if ("load".equals(terminalNo)) {
            return getDataTable(dataService.getLoadsData());
        } else {
            System.out.println("terminalNo："+terminalNo+" error");
            return null;
        }
    }


}

