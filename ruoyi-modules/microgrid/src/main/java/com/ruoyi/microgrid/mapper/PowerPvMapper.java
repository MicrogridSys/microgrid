package com.ruoyi.microgrid.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.microgrid.domain.PowerPv;
import org.apache.ibatis.annotations.Param;

/**
 * power_pvMapper接口
 * 
 * @author zxs
 * @date 2023-05-23
 */
public interface PowerPvMapper 
{
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
     * @param timestamp power_pv时间戳
     * @return power_pv集合
     * */
    List<PowerPv> getRecentPowerPvData(@Param("timestamp") Date timestamp);

    /**
     * 获取pv总发电数据
     * @return power_pv_total
     */
    BigDecimal getTotalPowerPvData();

    List<?> getPvData();
}
