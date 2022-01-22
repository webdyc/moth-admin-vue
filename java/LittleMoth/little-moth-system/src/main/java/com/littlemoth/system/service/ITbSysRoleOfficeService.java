package com.littlemoth.system.service;

import com.littlemoth.system.domain.TbSysRoleOffice;

import java.util.List;

/**
 * 角色-机构Service接口
 * 
 * @author xjl
 * @date 2022-01-20
 */
public interface ITbSysRoleOfficeService 
{
    /**
     * 查询角色-机构
     * 
     * @param id 角色-机构主键
     * @return 角色-机构
     */
    public TbSysRoleOffice selectTbSysRoleOfficeById(Long id);

    /**
     * 查询角色-机构列表
     * 
     * @param tbSysRoleOffice 角色-机构
     * @return 角色-机构集合
     */
    public List<TbSysRoleOffice> selectTbSysRoleOfficeList(TbSysRoleOffice tbSysRoleOffice);

    /**
     * 新增角色-机构
     * 
     * @param tbSysRoleOffice 角色-机构
     * @return 结果
     */
    public int insertTbSysRoleOffice(TbSysRoleOffice tbSysRoleOffice);

    /**
     * 修改角色-机构
     * 
     * @param tbSysRoleOffice 角色-机构
     * @return 结果
     */
    public int updateTbSysRoleOffice(TbSysRoleOffice tbSysRoleOffice);

    /**
     * 批量删除角色-机构
     * 
     * @param ids 需要删除的角色-机构主键集合
     * @return 结果
     */
    public int deleteTbSysRoleOfficeByIds(Long[] ids);

    /**
     * 删除角色-机构信息
     * 
     * @param id 角色-机构主键
     * @return 结果
     */
    public int deleteTbSysRoleOfficeById(Long id);
}
