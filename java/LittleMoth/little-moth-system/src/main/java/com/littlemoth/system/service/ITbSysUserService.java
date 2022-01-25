package com.littlemoth.system.service;

import com.littlemoth.common.core.domain.model.TbSysUser;

import java.util.List;

/**
 * 用户Service接口
 * 
 * @author xusan
 * @date 2022-01-12
 */
public interface ITbSysUserService 
{
    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    public TbSysUser selectTbSysUserById(Long id);

    /**
     * 查询用户列表
     * 
     * @param tbSysUser 用户
     * @return 用户集合
     */
    public List<TbSysUser> selectTbSysUserList(TbSysUser tbSysUser);

    /**
     * 新增用户
     * 
     * @param tbSysUser 用户
     * @return 结果
     */
    public int insertTbSysUser(TbSysUser tbSysUser);

    /**
     * 修改用户
     * 
     * @param tbSysUser 用户
     * @return 结果
     */
    public int updateTbSysUser(TbSysUser tbSysUser);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键集合
     * @return 结果
     */
    public int deleteTbSysUserByIds(Long[] ids);

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    public int deleteTbSysUserById(Long id);
}
