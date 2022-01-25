package com.littlemoth.system.service;

import com.littlemoth.system.domain.TbSysRole;

import java.util.List;

/**
 * 角色Service接口
 * 
 * @author xjl
 * @date 2022-01-19
 */
public interface ITbSysRoleService 
{
    /**
     * 查询角色
     * 
     * @param id 角色主键
     * @return 角色
     */
    public TbSysRole selectTbSysRoleById(Long id);

    /**
     * 查询角色列表
     * 
     * @param tbSysRole 角色
     * @return 角色集合
     */
    public List<TbSysRole> selectTbSysRoleList(TbSysRole tbSysRole);

    /**
     * 新增角色
     * 
     * @param tbSysRole 角色
     * @return 结果
     */
    public int insertTbSysRole(TbSysRole tbSysRole);

    /**
     * 修改角色
     * 
     * @param tbSysRole 角色
     * @return 结果
     */
    public int updateTbSysRole(TbSysRole tbSysRole);

    /**
     * 批量删除角色
     * 
     * @param ids 需要删除的角色主键集合
     * @return 结果
     */
    public int deleteTbSysRoleByIds(Long[] ids);

    /**
     * 删除角色信息
     * 
     * @param id 角色主键
     * @return 结果
     */
    public int deleteTbSysRoleById(Long id);
}
