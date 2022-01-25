package com.littlemoth.system.service.impl;

import com.littlemoth.system.domain.TbSysRoleMenu;
import com.littlemoth.system.mapper.TbSysRoleMenuMapper;
import com.littlemoth.system.service.ITbSysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色-菜单Service业务层处理
 * 
 * @author xjl
 * @date 2022-01-20
 */
@Service
public class TbSysRoleMenuServiceImpl implements ITbSysRoleMenuService
{
    @Autowired
    private TbSysRoleMenuMapper tbSysRoleMenuMapper;

    /**
     * 查询角色-菜单
     * 
     * @param id 角色-菜单主键
     * @return 角色-菜单
     */
    @Override
    public TbSysRoleMenu selectTbSysRoleMenuById(Long id)
    {
        return tbSysRoleMenuMapper.selectTbSysRoleMenuById(id);
    }

    /**
     * 查询角色-菜单列表
     * 
     * @param tbSysRoleMenu 角色-菜单
     * @return 角色-菜单
     */
    @Override
    public List<TbSysRoleMenu> selectTbSysRoleMenuList(TbSysRoleMenu tbSysRoleMenu)
    {
        return tbSysRoleMenuMapper.selectTbSysRoleMenuList(tbSysRoleMenu);
    }

    /**
     * 新增角色-菜单
     * 
     * @param tbSysRoleMenu 角色-菜单
     * @return 结果
     */
    @Override
    public int insertTbSysRoleMenu(TbSysRoleMenu tbSysRoleMenu)
    {
        return tbSysRoleMenuMapper.insertTbSysRoleMenu(tbSysRoleMenu);
    }

    /**
     * 修改角色-菜单
     * 
     * @param tbSysRoleMenu 角色-菜单
     * @return 结果
     */
    @Override
    public int updateTbSysRoleMenu(TbSysRoleMenu tbSysRoleMenu)
    {
        return tbSysRoleMenuMapper.updateTbSysRoleMenu(tbSysRoleMenu);
    }

    /**
     * 批量删除角色-菜单
     * 
     * @param ids 需要删除的角色-菜单主键
     * @return 结果
     */
    @Override
    public int deleteTbSysRoleMenuByIds(Long[] ids)
    {
        return tbSysRoleMenuMapper.deleteTbSysRoleMenuByIds(ids);
    }

    /**
     * 删除角色-菜单信息
     * 
     * @param id 角色-菜单主键
     * @return 结果
     */
    @Override
    public int deleteTbSysRoleMenuById(Long id)
    {
        return tbSysRoleMenuMapper.deleteTbSysRoleMenuById(id);
    }
}
