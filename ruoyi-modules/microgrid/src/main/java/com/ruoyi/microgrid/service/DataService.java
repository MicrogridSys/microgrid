package com.ruoyi.microgrid.service;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.microgrid.domain.*;

/**
 * power_genService接口
 * 
 * @author ruoyi
 * @date 2023-05-19
 */
public interface DataService
{

    /**
     * 新增power_gen
     *
     * @param powerGen power_gen
     * @return 结果
     */
    public int insertPowerGen(PowerGen powerGen);

    /**
     * 获取最近更新的发电机数据
     *
     * @return power_gen集合
     */
    public List<PowerGen> getRecentPowerGenData();

    /******************************************************************************************/
    // pv
    /**
     * 新增power_pv
     *
     * @param powerPv power_pv
     * @return 结果
     */
    public int insertPowerPv(PowerPv powerPv);

    /**
     * 获取最近更新的pv数据
     *
     * @return power_pv集合
     */
    public List<PowerPv> getRecentPowerPvData();

    /******************************************************************************************/
    // wind
    /**
     * 新增power_wind
     *
     * @param powerWind power_wind
     * @return 结果
     */
    public int insertPowerWind(PowerWind powerWind);

    /**
     * 获取最近更新的wind数据
     * @return power_wind集合
     */
    public List<PowerWind> getRecentPowerWindData();

    /******************************************************************************************/
    // energy
    /**
     * 新增power_energy
     * @param powerEnergy power_energy
     * @return 结果
     */
    public int insertPowerEnergy(PowerEnergy powerEnergy);

    /**
     * 获取最近更新的energy数据
     * @return power_energy集合
     */
    public List<PowerEnergy> getRecentPowerEnergyData();

    /******************************************************************************************/
    // loads
    /**
     * 新增loads
     * @param loads loads
     * @return 结果
     */
    public int insertLoads(Loads loads);

    /**
     * 获取最近更新的loads数据
     * @return loads集合
     */
    public List<Loads> getRecentLoadsData();

    /******************************************************************************************/
    // MainGrid
    /**
     * 新增grid_main
     * @param mainGrid grid_main
     * @return 结果
     */
    public int insertMainGrid(MainGrid mainGrid);

    /**
     * 获取最近更新的grid_main数据
     * @return grid_main集合
     */
    public List<MainGrid> getRecentMainGridData();

    public BigDecimal getTotalPowerPvData();
    public BigDecimal getTotalPowerWindData();

    List<?> getGenData(int start, int pageSize);

    List<?> getWindData();

    List<?> getPvData();

    List<?> getEnergyData();

    List<?> getMainGridData();

    List<?> getLoadsData();

    long getTotal();
}
