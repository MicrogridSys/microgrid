package com.ruoyi.microgrid.mapper;

import java.util.List;
import com.ruoyi.microgrid.domain.Terminals;

/**
 * 终端表Mapper接口
 * 
 * @author zxs
 * @date 2023-05-31
 */
public interface TerminalsMapper 
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
     * 删除终端表
     * 
     * @param id 终端表主键
     * @return 结果
     */
    public int deleteTerminalsById(Long id);

    /**
     * 批量删除终端表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTerminalsByIds(Long[] ids);

    List<Terminals> getTerminalList(String terminalNo);
}
