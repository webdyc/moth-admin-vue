package com.littlemoth.system.service;

import com.littlemoth.common.core.domain.TbSysOffice;
import com.littlemoth.common.core.domain.TreeSelect;
import com.littlemoth.common.core.domain.model.TbSysUser;

import java.util.List;

/**
 * 机构Service接口
 *
 * @author xusan
 * @date 2022-01-12
 */
public interface ITbSysOfficeService {
    /**
     * 查询机构
     *
     * @param id 机构主键
     * @return 机构
     */
    public TbSysOffice selectTbSysOfficeById(Long id);

    /**
     * 查询机构列表
     *
     * @param tbSysOffice 机构
     * @return 机构集合
     */
    public List<TbSysOffice> selectTbSysOfficeList(TbSysOffice tbSysOffice, TbSysUser user);

    /**
     * 构建前端所需要树结构
     *
     * @param offices 部门列表
     * @return 树结构列表
     */
    public List<TbSysOffice> buildOfficeTree(List<TbSysOffice> offices);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param offices 部门列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildSysOfficeTreeSelect(List<TbSysOffice> offices);

    /**
     * 新增机构
     *
     * @param tbSysOffice 机构
     * @return 结果
     */
    public int insertTbSysOffice(TbSysOffice tbSysOffice, TbSysUser user);

    /**
     * 修改机构
     *
     * @param tbSysOffice 机构
     * @return 结果
     */
    public int updateTbSysOffice(TbSysOffice tbSysOffice, TbSysUser user);

    /**
     * 批量删除机构
     *
     * @param ids 需要删除的机构主键集合
     * @return 结果
     */
    public int deleteTbSysOfficeByIds(Long[] ids);

    /**
     * 删除机构信息
     *
     * @param id 机构主键
     * @return 结果
     */
    public int deleteTbSysOfficeById(Long id);
}
