package com.ruoyi.microgrid.mapper;

import com.ruoyi.microgrid.domain.PowerWind;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * power_windMapper接口
 * 
 * @author zxs
 * @date 2023-05-23
 */
public interface PowerWindMapper 
{
    /**
     * 新增power_wind
     *
     * @param powerWind power_wind
     * @return 结果
     */
    public int insertPowerWind(PowerWind powerWind);

    /**
     * 获取最近更新的wind数据
     *
     * @param timestamp power_wind时间戳
     * @return power_wind集合
     * */
    List<PowerWind> getRecentPowerWindData(@Param("timestamp") Date timestamp);

    BigDecimal getTotalPowerWindData();

    List<?> getWindData();
}
