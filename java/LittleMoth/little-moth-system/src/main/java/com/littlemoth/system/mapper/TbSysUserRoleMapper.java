package com.littlemoth.system.mapper;

import com.littlemoth.system.domain.TbSysUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户-组织-角色Mapper接口
 * 
 * @author xjl
 * @date 2022-01-20
 */
@Repository
public interface TbSysUserRoleMapper 
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
     * 删除用户-组织-角色
     * 
     * @param id 用户-组织-角色主键
     * @return 结果
     */
    public int deleteTbSysUserRoleById(Long id);

    /**
     * 批量删除用户-组织-角色
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbSysUserRoleByIds(Long[] ids);
}
