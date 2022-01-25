package com.littlemoth.system.service;

import com.littlemoth.common.core.domain.TreeSelect;
import com.littlemoth.common.core.domain.entity.TbSysMenu;
import com.littlemoth.common.core.domain.model.LoginUser;
import com.littlemoth.common.core.domain.model.TbSysUser;
import com.littlemoth.system.domain.vo.TbRouterVo;

import java.util.List;

/**
 * 菜单Service接口
 * 
 * @author xusan
 * @date 2022-01-12
 */
public interface ITbSysMenuService 
{
    /**
     * 查询菜单
     * 
     * @param id 菜单主键
     * @return 菜单
     */
    public TbSysMenu selectTbSysMenuById(Long id);

    /**
     * 查询菜单列表
     * 
     * @param tbSysMenu 菜单
     * @return 菜单集合
     */
    public List<TbSysMenu> selectTbSysMenuList(TbSysMenu tbSysMenu);

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu 菜单信息
     * @param user 用户
     * @return 菜单列表
     */
    public List<TbSysMenu> selectTbSysMenuList(TbSysMenu menu, TbSysUser user);

    /**
     * 新增菜单
     * 
     * @param tbSysMenu 菜单
     * @return 结果
     */
    public int insertTbSysMenu(TbSysMenu tbSysMenu);

    /**
     * 修改菜单
     * 
     * @param tbSysMenu 菜单
     * @return 结果
     */
    public int updateTbSysMenu(TbSysMenu tbSysMenu);

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    public List<TbSysMenu> buildMenuTree(List<TbSysMenu> menus);




    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildMenuTreeSelect(List<TbSysMenu> menus);

    /**
     * 批量删除菜单
     * 
     * @param ids 需要删除的菜单主键集合
     * @return 结果
     */
    public int deleteTbSysMenuByIds(Long[] ids);

    /**
     * 删除菜单信息
     * 
     * @param id 菜单主键
     * @return 结果
     */
    public int deleteTbSysMenuById(Long id);

    /**
     * 校验菜单名称是否唯一
     *
     * @param tbSysMenu 菜单信息
     * @return 结果
     */
    public String checkMenuNameUnique(TbSysMenu tbSysMenu);


    /**
     * 根据用户ID查询菜单树信息
     *
     * @param loginUser 用户ID
     * @return 菜单列表
     */
    public List<TbSysMenu> selectMenuTreeByUserId(LoginUser loginUser);


    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    public List<TbRouterVo> buildMenus(List<TbSysMenu> menus,String[] target);



    /**
     * 构建前端树所需要的菜单
     *
     * @param list 菜单列表
     * @return 路由列表
     */
    List<TbSysMenu> selectTbSysMenuListTree(List<TbSysMenu> list);

}
