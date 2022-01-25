package com.littlemoth.system.mapper;

import com.littlemoth.system.domain.TbSysRoleOffice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色-机构Mapper接口
 * 
 * @author xjl
 * @date 2022-01-20
 */
@Repository
public interface TbSysRoleOfficeMapper 
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
     * 删除角色-机构
     * 
     * @param id 角色-机构主键
     * @return 结果
     */
    public int deleteTbSysRoleOfficeById(Long id);

    /**
     * 批量删除角色-机构
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbSysRoleOfficeByIds(Long[] ids);
}
