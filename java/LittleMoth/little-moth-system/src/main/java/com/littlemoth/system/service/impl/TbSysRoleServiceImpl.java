package com.littlemoth.system.service.impl;

import com.littlemoth.common.utils.DateUtils;
import com.littlemoth.system.domain.TbSysRole;
import com.littlemoth.system.mapper.TbSysRoleMapper;
import com.littlemoth.system.service.ITbSysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色Service业务层处理
 * 
 * @author xjl
 * @date 2022-01-19
 */
@Service
public class TbSysRoleServiceImpl implements ITbSysRoleService
{
    @Autowired
    private TbSysRoleMapper tbSysRoleMapper;

    /**
     * 查询角色
     * 
     * @param id 角色主键
     * @return 角色
     */
    @Override
    public TbSysRole selectTbSysRoleById(Long id)
    {
        return tbSysRoleMapper.selectTbSysRoleById(id);
    }

    /**
     * 查询角色列表
     * 
     * @param tbSysRole 角色
     * @return 角色
     */
    @Override
    public List<TbSysRole> selectTbSysRoleList(TbSysRole tbSysRole)
    {
        return tbSysRoleMapper.selectTbSysRoleList(tbSysRole);
    }

    /**
     * 新增角色
     * 
     * @param tbSysRole 角色
     * @return 结果
     */
    @Override
    public int insertTbSysRole(TbSysRole tbSysRole)
    {
        tbSysRole.setCreateTime(DateUtils.getNowDate());
        return tbSysRoleMapper.insertTbSysRole(tbSysRole);
    }

    /**
     * 修改角色
     * 
     * @param tbSysRole 角色
     * @return 结果
     */
    @Override
    public int updateTbSysRole(TbSysRole tbSysRole)
    {
        tbSysRole.setUpdateTime(DateUtils.getNowDate());
        return tbSysRoleMapper.updateTbSysRole(tbSysRole);
    }

    /**
     * 批量删除角色
     * 
     * @param ids 需要删除的角色主键
     * @return 结果
     */
    @Override
    public int deleteTbSysRoleByIds(Long[] ids)
    {
        return tbSysRoleMapper.deleteTbSysRoleByIds(ids);
    }

    /**
     * 删除角色信息
     * 
     * @param id 角色主键
     * @return 结果
     */
    @Override
    public int deleteTbSysRoleById(Long id)
    {
        return tbSysRoleMapper.deleteTbSysRoleById(id);
    }
}
