package com.littlemoth.system.service.impl;

import com.littlemoth.system.domain.TbSysUserRole;
import com.littlemoth.system.mapper.TbSysUserRoleMapper;
import com.littlemoth.system.service.ITbSysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户-组织-角色Service业务层处理
 * 
 * @author xjl
 * @date 2022-01-20
 */
@Service
public class TbSysUserRoleServiceImpl implements ITbSysUserRoleService
{
    @Autowired
    private TbSysUserRoleMapper tbSysUserRoleMapper;

    /**
     * 查询用户-组织-角色
     * 
     * @param id 用户-组织-角色主键
     * @return 用户-组织-角色
     */
    @Override
    public TbSysUserRole selectTbSysUserRoleById(Long id)
    {
        return tbSysUserRoleMapper.selectTbSysUserRoleById(id);
    }

    /**
     * 查询用户-组织-角色列表
     * 
     * @param tbSysUserRole 用户-组织-角色
     * @return 用户-组织-角色
     */
    @Override
    public List<TbSysUserRole> selectTbSysUserRoleList(TbSysUserRole tbSysUserRole)
    {
        return tbSysUserRoleMapper.selectTbSysUserRoleList(tbSysUserRole);
    }

    /**
     * 新增用户-组织-角色
     * 
     * @param tbSysUserRole 用户-组织-角色
     * @return 结果
     */
    @Override
    public int insertTbSysUserRole(TbSysUserRole tbSysUserRole)
    {
        return tbSysUserRoleMapper.insertTbSysUserRole(tbSysUserRole);
    }

    /**
     * 修改用户-组织-角色
     * 
     * @param tbSysUserRole 用户-组织-角色
     * @return 结果
     */
    @Override
    public int updateTbSysUserRole(TbSysUserRole tbSysUserRole)
    {
        return tbSysUserRoleMapper.updateTbSysUserRole(tbSysUserRole);
    }

    /**
     * 批量删除用户-组织-角色
     * 
     * @param ids 需要删除的用户-组织-角色主键
     * @return 结果
     */
    @Override
    public int deleteTbSysUserRoleByIds(Long[] ids)
    {
        return tbSysUserRoleMapper.deleteTbSysUserRoleByIds(ids);
    }

    /**
     * 删除用户-组织-角色信息
     * 
     * @param id 用户-组织-角色主键
     * @return 结果
     */
    @Override
    public int deleteTbSysUserRoleById(Long id)
    {
        return tbSysUserRoleMapper.deleteTbSysUserRoleById(id);
    }
}
