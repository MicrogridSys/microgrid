package com.ruoyi.microgrid.mapper;

import com.ruoyi.microgrid.domain.MainGrid;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface MainGridMapper {

    /**
     * 新增grid_main数据
     *
     * @param mainGrid grid_main
     * @return 结果
     */
    public int insertMainGrid(MainGrid mainGrid);

    /**
     * 获取最近更新的发电机数据
     *
     * @param timestamp grid_main时间戳
     * @return grid_main集合
     * */
    List<MainGrid> getRecentMainGridData(@Param("timestamp") Date timestamp);

    List<?> getMainGridData();
}
