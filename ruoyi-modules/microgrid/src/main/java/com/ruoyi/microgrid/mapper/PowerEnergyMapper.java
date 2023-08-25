package com.ruoyi.microgrid.mapper;

import com.ruoyi.microgrid.domain.PowerEnergy;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * power_energyMapper接口
 *
 * @author zxs
 * @date 2023-05-19
 */
public interface PowerEnergyMapper {

    /**
     * 新增power_energy数据
     * @param powerEnergy
     * @return
     */
    public int insertPowerEnergy(PowerEnergy powerEnergy);

    /**
     * 获取最近更新的发电机数据
     *
     * @param timestamp power_gen时间戳
     * @return power_gen集合
     * */
    List<PowerEnergy> getRecentPowerEnergyData(@Param("timestamp") Date timestamp);

    List<?> getEnergyData();
}
