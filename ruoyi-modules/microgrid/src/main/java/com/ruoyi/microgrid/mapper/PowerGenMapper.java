package com.ruoyi.microgrid.mapper;

import java.util.Date;
import java.util.List;

import com.ruoyi.microgrid.domain.PowerGen;
import org.apache.ibatis.annotations.Param;


public interface PowerGenMapper {

    /**
     * 新增power_gen数据
     *
     * @param powerGen power_gen
     * @return 结果
     */
    int insertPowerGen(PowerGen powerGen);

    /**
     * 获取最近更新的发电机数据
     *
     * @param timestamp power_gen时间戳
     * @return power_gen集合
     * */
    List<PowerGen> getRecentPowerGenData(@Param("timestamp") Date timestamp);

    List<?> getGenData(@Param("start") int start, @Param("pageSize") int pageSize);

    long getTotal();
}
