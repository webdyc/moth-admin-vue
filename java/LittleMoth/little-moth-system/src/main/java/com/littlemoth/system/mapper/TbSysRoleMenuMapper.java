package com.littlemoth.system.mapper;

import com.littlemoth.system.domain.TbSysRoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色-菜单Mapper接口
 * 
 * @author xjl
 * @date 2022-01-20
 */
@Repository
public interface TbSysRoleMenuMapper 
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
     * 删除角色-菜单
     * 
     * @param id 角色-菜单主键
     * @return 结果
     */
    public int deleteTbSysRoleMenuById(Long id);

    /**
     * 批量删除角色-菜单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbSysRoleMenuByIds(Long[] ids);
}
