package com.ruoyi.microgrid.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ruoyi.microgrid.domain.*;
import com.ruoyi.microgrid.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.microgrid.service.DataService;

/**
 * power_genService业务层处理
 * 
 * @author zxs
 * @date 2023-05-19
 */
@Service
public class DataServiceImpl implements DataService
{
    @Autowired
    private PowerGenMapper powerGenMapper;

    @Autowired
    private PowerPvMapper powerPvMapper;

    @Autowired
    private PowerWindMapper powerWindMapper;

    @Autowired
    private PowerEnergyMapper powerEnergyMapper;

    @Autowired
    private LoadsMapper loadsMapper;

    @Autowired
    private MainGridMapper mainGridMapper;


    /******************************************************************************************************************/
    // 对于gen相关操作
    /**
     * 新增power_gen
     *
     * @param powerGen power_gen
     * @return 是否成功
     */
    @Override
    public int insertPowerGen(PowerGen powerGen)
    {
        return powerGenMapper.insertPowerGen(powerGen);
    }
    /**
     * 获取最近更新的发电机数据
     * @return power_gen集合
     * */
    @Override
    public List<PowerGen> getRecentPowerGenData() {
        return powerGenMapper.getRecentPowerGenData(getlastDate());
    }


    /******************************************************************************************************************/
    // 对于PV相关操作
    /**
     * 新增power_pv
     * @param powerPv power_pv
     * @return 是否成功
     */
    @Override
    public int insertPowerPv(PowerPv powerPv) {
        return powerPvMapper.insertPowerPv(powerPv);
    }
    /**
     * 获取最近更新的pv数据
     * @return power_pv集合
     */
    @Override
    public List<PowerPv> getRecentPowerPvData() {
       return powerPvMapper.getRecentPowerPvData(getlastDate());
    }


    // 对于wind相关操作
    /******************************************************************************************************************/
    /**
     * 新增power_wind
     * @param powerWind power_wind
     * @return 是否成功
     */
    @Override
    public int insertPowerWind(PowerWind powerWind) {
        return powerWindMapper.insertPowerWind(powerWind);
    }

    /**
     * 获取最近更新的wind数据
     * @return power_wind集合
     */
    @Override
    public List<PowerWind> getRecentPowerWindData() {
        return powerWindMapper.getRecentPowerWindData(getlastDate());
    }


    /******************************************************************************************************************/
    // 对于energy相关操作
    /**
     * 新增power_energy
     * @param powerEnergy power_energy
     * @return 0/1
     */
    @Override
    public int insertPowerEnergy(PowerEnergy powerEnergy) {
        return powerEnergyMapper.insertPowerEnergy(powerEnergy);
    }

    /**
     * 获取最近更新的energy数据
     * @return power_energy集合
     */
    @Override
    public List<PowerEnergy> getRecentPowerEnergyData() {
        return powerEnergyMapper.getRecentPowerEnergyData(getlastDate());
    }


    /******************************************************************************************************************/
    // 对于loads相关操作
    @Override
    public int insertLoads(Loads loads) {
        return loadsMapper.insertLoads(loads);
    }

    @Override
    public List<Loads> getRecentLoadsData() {
        return loadsMapper.getRecentLoadsData(getlastDate());
    }


    /******************************************************************************************************************/
    // 对于grid_main相关操作
    @Override
    public int insertMainGrid(MainGrid mainGrid) {
        return mainGridMapper.insertMainGrid(mainGrid);
    }

    @Override
    public List<MainGrid> getRecentMainGridData() {
        return mainGridMapper.getRecentMainGridData(getlastDate());
    }

    @Override
    public BigDecimal getTotalPowerPvData() {
        return powerPvMapper.getTotalPowerPvData();
    }

    @Override
    public BigDecimal getTotalPowerWindData() {
        return powerWindMapper.getTotalPowerWindData();
    }


    @Override
    public List<?> getGenData(int start, int pageSize) {
        return powerGenMapper.getGenData(start, pageSize);
    }

    @Override
    public List<?> getWindData() {
        return powerWindMapper.getWindData();
    }

    @Override
    public List<?> getPvData() {
        return powerPvMapper.getPvData();
    }

    @Override
    public List<?> getEnergyData() {
        return powerEnergyMapper.getEnergyData();
    }

    @Override
    public List<?> getMainGridData() {
        return mainGridMapper.getMainGridData();
    }

    @Override
    public List<?> getLoadsData() {
        return loadsMapper.getLoadsData();
    }

    @Override
    public long getTotal() {
        return powerGenMapper.getTotal();
    }


    /**
     * 工具函数-获取前6秒的时间
     * @return 6秒前的时间
     */
    private static Date getlastDate(){
        Calendar calendar = Calendar.getInstance();
        // 将 Calendar 的时间减去 6 秒
        calendar.add(Calendar.SECOND, -6);
        // 返回前6秒时的时间
        return  calendar.getTime();
    }
}
