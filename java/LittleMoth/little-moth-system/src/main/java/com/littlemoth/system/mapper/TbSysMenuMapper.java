package com.littlemoth.system.mapper;

import com.littlemoth.common.core.domain.entity.TbSysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单Mapper接口
 * 
 * @author xusan
 * @date 2022-01-12
 */
@Mapper
@Repository
public interface TbSysMenuMapper 
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
     * 查询菜单列表 根据MenuType
     *
     * @param tbSysMenu 菜单
     * @return 菜单集合
     */
    public List<TbSysMenu> selectTbSysMenuListByMenuType(TbSysMenu tbSysMenu);

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
     * 删除菜单
     * 
     * @param id 菜单主键
     * @return 结果
     */
    public int deleteTbSysMenuById(Long id);

    /**
     * 批量删除菜单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbSysMenuByIds(Long[] ids);

    /**
     * 校验菜单名称是否唯一
     *
     * @param menuName 菜单名称
     * @param parentId 父菜单ID
     * @return 结果
     */
    public TbSysMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") String parentId);


    /**
     * 根据用户ID查询菜单
     *
     * @return 菜单列表
     */
    public List<TbSysMenu> selectMenuTreeAll();

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<TbSysMenu> selectMenuTreeByUserId(Long userId);

}
