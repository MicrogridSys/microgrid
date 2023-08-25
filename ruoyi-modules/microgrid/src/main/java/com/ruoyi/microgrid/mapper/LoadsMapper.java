package com.ruoyi.microgrid.mapper;

import com.ruoyi.microgrid.domain.Loads;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface LoadsMapper {

    /**
     * 新增loads数据
     *
     * @param loads loads
     * @return 结果
     */
    int insertLoads(Loads loads);

    /**
     * 获取最近更新的loads数据
     *
     * @param timestamp loads时间戳
     * @return loads集合
     * */
    List<Loads> getRecentLoadsData(@Param("timestamp") Date timestamp);

    List<?> getLoadsData();
}
