package com.ruoyi.microgrid.service;

import java.util.List;
import com.ruoyi.microgrid.domain.Terminals;

/**
 * 终端表Service接口
 * 
 * @author zxs
 * @date 2023-05-31
 */
public interface ITerminalsService 
{
    /**
     * 查询终端表
     * 
     * @param id 终端表主键
     * @return 终端表
     */
    public Terminals selectTerminalsById(Long id);

    /**
     * 查询终端表列表
     * 
     * @param terminals 终端表
     * @return 终端表集合
     */
    public List<Terminals> selectTerminalsList(Terminals terminals);

    /**
     * 新增终端表
     * 
     * @param terminals 终端表
     * @return 结果
     */
    public int insertTerminals(Terminals terminals);

    /**
     * 修改终端表
     * 
     * @param terminals 终端表
     * @return 结果
     */
    public int updateTerminals(Terminals terminals);

    /**
     * 批量删除终端表
     * 
     * @param ids 需要删除的终端表主键集合
     * @return 结果
     */
    public int deleteTerminalsByIds(Long[] ids);

    /**
     * 删除终端表信息
     * 
     * @param id 终端表主键
     * @return 结果
     */
    public int deleteTerminalsById(Long id);

    List<Terminals> getTerminalList(String terminalNo);
}
