package com.ruoyi.microgrid.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.microgrid.mapper.TerminalsMapper;
import com.ruoyi.microgrid.domain.Terminals;
import com.ruoyi.microgrid.service.ITerminalsService;

/**
 * 终端表Service业务层处理
 * 
 * @author zxs
 * @date 2023-05-31
 */
@Service
public class TerminalsServiceImpl implements ITerminalsService 
{
    @Autowired
    private TerminalsMapper terminalsMapper;

    /**
     * 查询终端表
     * 
     * @param id 终端表主键
     * @return 终端表
     */
    @Override
    public Terminals selectTerminalsById(Long id)
    {
        return terminalsMapper.selectTerminalsById(id);
    }

    /**
     * 查询终端表列表
     * 
     * @param terminals 终端表
     * @return 终端表
     */
    @Override
    public List<Terminals> selectTerminalsList(Terminals terminals)
    {
        return terminalsMapper.selectTerminalsList(terminals);
    }

    /**
     * 新增终端表
     * 
     * @param terminals 终端表
     * @return 结果
     */
    @Override
    public int insertTerminals(Terminals terminals)
    {
        return terminalsMapper.insertTerminals(terminals);
    }

    /**
     * 修改终端表
     * 
     * @param terminals 终端表
     * @return 结果
     */
    @Override
    public int updateTerminals(Terminals terminals)
    {
        return terminalsMapper.updateTerminals(terminals);
    }

    /**
     * 批量删除终端表
     * 
     * @param ids 需要删除的终端表主键
     * @return 结果
     */
    @Override
    public int deleteTerminalsByIds(Long[] ids)
    {
        return terminalsMapper.deleteTerminalsByIds(ids);
    }

    /**
     * 删除终端表信息
     * 
     * @param id 终端表主键
     * @return 结果
     */
    @Override
    public int deleteTerminalsById(Long id)
    {
        return terminalsMapper.deleteTerminalsById(id);
    }

    @Override
    public List<Terminals> getTerminalList(String terminalNo) {
        return terminalsMapper.getTerminalList("data_"+terminalNo+"%");
    }
}
