package com.littlemoth.system.service.impl;

import com.littlemoth.common.utils.DateUtils;
import com.littlemoth.system.domain.TbSysOffice;
import com.littlemoth.system.mapper.TbSysOfficeMapper;
import com.littlemoth.system.service.ITbSysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机构Service业务层处理
 * 
 * @author jiyanjie
 * @date 2022-01-12
 */
@Service
public class TbSysOfficeServiceImpl implements ITbSysOfficeService
{
    @Autowired
    private TbSysOfficeMapper tbSysOfficeMapper;

    /**
     * 查询机构
     * 
     * @param id 机构主键
     * @return 机构
     */
    @Override
    public TbSysOffice selectTbSysOfficeById(Long id)
    {
        return tbSysOfficeMapper.selectTbSysOfficeById(id);
    }

    /**
     * 查询机构列表
     * 
     * @param tbSysOffice 机构
     * @return 机构
     */
    @Override
    public List<TbSysOffice> selectTbSysOfficeList(TbSysOffice tbSysOffice)
    {
        return tbSysOfficeMapper.selectTbSysOfficeList(tbSysOffice);
    }

    /**
     * 新增机构
     * 
     * @param tbSysOffice 机构
     * @return 结果
     */
    @Override
    public int insertTbSysOffice(TbSysOffice tbSysOffice)
    {
        return tbSysOfficeMapper.insertTbSysOffice(tbSysOffice);
    }

    /**
     * 修改机构
     * 
     * @param tbSysOffice 机构
     * @return 结果
     */
    @Override
    public int updateTbSysOffice(TbSysOffice tbSysOffice)
    {
        tbSysOffice.setUpdateTime(DateUtils.getNowDate());
        return tbSysOfficeMapper.updateTbSysOffice(tbSysOffice);
    }

    /**
     * 批量删除机构
     * 
     * @param ids 需要删除的机构主键
     * @return 结果
     */
    @Override
    public int deleteTbSysOfficeByIds(Long[] ids)
    {
        return tbSysOfficeMapper.deleteTbSysOfficeByIds(ids);
    }

    /**
     * 删除机构信息
     * 
     * @param id 机构主键
     * @return 结果
     */
    @Override
    public int deleteTbSysOfficeById(Long id)
    {
        return tbSysOfficeMapper.deleteTbSysOfficeById(id);
    }
}
