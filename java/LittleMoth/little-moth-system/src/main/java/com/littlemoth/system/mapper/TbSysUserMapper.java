package com.littlemoth.system.mapper;

import com.littlemoth.common.core.domain.model.TbSysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户Mapper接口
 * 
 * @author xusan
 * @date 2022-01-12
 */
@Repository
public interface TbSysUserMapper 
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
     * 删除用户
     * 
     * @param id 用户主键
     * @return 结果
     */
    public int deleteTbSysUserById(Long id);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbSysUserByIds(Long[] ids);

    /**
     * 查询组织下大于lastTime的用户
     * @param officeIds 组织ids
     * @param lastTime  最后更新时间
     * @return
     */
    List<TbSysUser> getUserList(@Param("officeIds") List<Integer> officeIds,
                               @Param("lastTime") String lastTime);
}
