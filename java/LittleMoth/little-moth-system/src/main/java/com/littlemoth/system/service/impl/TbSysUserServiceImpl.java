package com.littlemoth.system.service.impl;

import com.littlemoth.common.core.domain.model.TbSysUser;
import com.littlemoth.common.utils.DateUtils;
import com.littlemoth.system.mapper.TbSysUserMapper;
import com.littlemoth.system.service.ITbSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户Service业务层处理
 * 
 * @author xusan
 * @date 2022-01-12
 */
@Service
public class TbSysUserServiceImpl implements ITbSysUserService
{
    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public TbSysUser selectTbSysUserById(Long id)
    {
        return tbSysUserMapper.selectTbSysUserById(id);
    }

    /**
     * 查询用户列表
     * 
     * @param tbSysUser 用户
     * @return 用户
     */
    @Override
    public List<TbSysUser> selectTbSysUserList(TbSysUser tbSysUser)
    {
        return tbSysUserMapper.selectTbSysUserList(tbSysUser);
    }

    /**
     * 新增用户
     * 
     * @param tbSysUser 用户
     * @return 结果
     */
    @Override
    public int insertTbSysUser(TbSysUser tbSysUser)
    {
        tbSysUser.setCreateTime(DateUtils.getNowDate());
        return tbSysUserMapper.insertTbSysUser(tbSysUser);
    }

    /**
     * 修改用户
     * 
     * @param tbSysUser 用户
     * @return 结果
     */
    @Override
    public int updateTbSysUser(TbSysUser tbSysUser)
    {
        tbSysUser.setUpdateTime(DateUtils.getNowDate());
        return tbSysUserMapper.updateTbSysUser(tbSysUser);
    }

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteTbSysUserByIds(Long[] ids)
    {
        return tbSysUserMapper.deleteTbSysUserByIds(ids);
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteTbSysUserById(Long id)
    {
        return tbSysUserMapper.deleteTbSysUserById(id);
    }
}
