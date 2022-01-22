package com.littlemoth.system.service;

import com.littlemoth.system.domain.TbSysRoleMenu;

import java.util.List;

/**
 * 角色-菜单Service接口
 * 
 * @author xjl
 * @date 2022-01-20
 */
public interface ITbSysRoleMenuService 
{
    /**
     * 查询角色-菜单
     * 
     * @param id 角色-菜单主键
     * @return 角色-菜单
     */
    public TbSysRoleMenu selectTbSysRoleMenuById(Long id);

    /**
     * 查询角色-菜单列表
     * 
     * @param tbSysRoleMenu 角色-菜单
     * @return 角色-菜单集合
     */
    public List<TbSysRoleMenu> selectTbSysRoleMenuList(TbSysRoleMenu tbSysRoleMenu);

    /**
     * 新增角色-菜单
     * 
     * @param tbSysRoleMenu 角色-菜单
     * @return 结果
     */
    public int insertTbSysRoleMenu(TbSysRoleMenu tbSysRoleMenu);

    /**
     * 修改角色-菜单
     * 
     * @param tbSysRoleMenu 角色-菜单
     * @return 结果
     */
    public int updateTbSysRoleMenu(TbSysRoleMenu tbSysRoleMenu);

    /**
     * 批量删除角色-菜单
     * 
     * @param ids 需要删除的角色-菜单主键集合
     * @return 结果
     */
    public int deleteTbSysRoleMenuByIds(Long[] ids);

    /**
     * 删除角色-菜单信息
     * 
     * @param id 角色-菜单主键
     * @return 结果
     */
    public int deleteTbSysRoleMenuById(Long id);
}
