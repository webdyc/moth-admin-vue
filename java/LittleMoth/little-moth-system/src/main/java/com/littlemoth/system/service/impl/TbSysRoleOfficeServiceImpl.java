package com.littlemoth.system.service.impl;

import com.littlemoth.system.domain.TbSysRoleOffice;
import com.littlemoth.system.mapper.TbSysRoleOfficeMapper;
import com.littlemoth.system.service.ITbSysRoleOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色-机构Service业务层处理
 * 
 * @author xjl
 * @date 2022-01-20
 */
@Service
public class TbSysRoleOfficeServiceImpl implements ITbSysRoleOfficeService
{
    @Autowired
    private TbSysRoleOfficeMapper tbSysRoleOfficeMapper;

    /**
     * 查询角色-机构
     * 
     * @param id 角色-机构主键
     * @return 角色-机构
     */
    @Override
    public TbSysRoleOffice selectTbSysRoleOfficeById(Long id)
    {
        return tbSysRoleOfficeMapper.selectTbSysRoleOfficeById(id);
    }

    /**
     * 查询角色-机构列表
     * 
     * @param tbSysRoleOffice 角色-机构
     * @return 角色-机构
     */
    @Override
    public List<TbSysRoleOffice> selectTbSysRoleOfficeList(TbSysRoleOffice tbSysRoleOffice)
    {
        return tbSysRoleOfficeMapper.selectTbSysRoleOfficeList(tbSysRoleOffice);
    }

    /**
     * 新增角色-机构
     * 
     * @param tbSysRoleOffice 角色-机构
     * @return 结果
     */
    @Override
    public int insertTbSysRoleOffice(TbSysRoleOffice tbSysRoleOffice)
    {
        return tbSysRoleOfficeMapper.insertTbSysRoleOffice(tbSysRoleOffice);
    }

    /**
     * 修改角色-机构
     * 
     * @param tbSysRoleOffice 角色-机构
     * @return 结果
     */
    @Override
    public int updateTbSysRoleOffice(TbSysRoleOffice tbSysRoleOffice)
    {
        return tbSysRoleOfficeMapper.updateTbSysRoleOffice(tbSysRoleOffice);
    }

    /**
     * 批量删除角色-机构
     * 
     * @param ids 需要删除的角色-机构主键
     * @return 结果
     */
    @Override
    public int deleteTbSysRoleOfficeByIds(Long[] ids)
    {
        return tbSysRoleOfficeMapper.deleteTbSysRoleOfficeByIds(ids);
    }

    /**
     * 删除角色-机构信息
     * 
     * @param id 角色-机构主键
     * @return 结果
     */
    @Override
    public int deleteTbSysRoleOfficeById(Long id)
    {
        return tbSysRoleOfficeMapper.deleteTbSysRoleOfficeById(id);
    }
}
