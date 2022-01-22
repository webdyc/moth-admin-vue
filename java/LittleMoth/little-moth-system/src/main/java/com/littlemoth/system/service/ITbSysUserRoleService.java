package com.littlemoth.system.service;

import com.littlemoth.system.domain.TbSysUserRole;

import java.util.List;

/**
 * 用户-组织-角色Service接口
 * 
 * @author xjl
 * @date 2022-01-20
 */
public interface ITbSysUserRoleService 
{
    /**
     * 查询用户-组织-角色
     * 
     * @param id 用户-组织-角色主键
     * @return 用户-组织-角色
     */
    public TbSysUserRole selectTbSysUserRoleById(Long id);

    /**
     * 查询用户-组织-角色列表
     * 
     * @param tbSysUserRole 用户-组织-角色
     * @return 用户-组织-角色集合
     */
    public List<TbSysUserRole> selectTbSysUserRoleList(TbSysUserRole tbSysUserRole);

    /**
     * 新增用户-组织-角色
     * 
     * @param tbSysUserRole 用户-组织-角色
     * @return 结果
     */
    public int insertTbSysUserRole(TbSysUserRole tbSysUserRole);

    /**
     * 修改用户-组织-角色
     * 
     * @param tbSysUserRole 用户-组织-角色
     * @return 结果
     */
    public int updateTbSysUserRole(TbSysUserRole tbSysUserRole);

    /**
     * 批量删除用户-组织-角色
     * 
     * @param ids 需要删除的用户-组织-角色主键集合
     * @return 结果
     */
    public int deleteTbSysUserRoleByIds(Long[] ids);

    /**
     * 删除用户-组织-角色信息
     * 
     * @param id 用户-组织-角色主键
     * @return 结果
     */
    public int deleteTbSysUserRoleById(Long id);
}
