package com.littlemoth.system.service;

import com.littlemoth.system.domain.TbSysOffice;

import java.util.List;

/**
 * 机构Service接口
 * 
 * @author jiyanjie
 * @date 2022-01-12
 */
public interface ITbSysOfficeService 
{
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
    public List<TbSysOffice> selectTbSysOfficeList(TbSysOffice tbSysOffice);

    /**
     * 新增机构
     * 
     * @param tbSysOffice 机构
     * @return 结果
     */
    public int insertTbSysOffice(TbSysOffice tbSysOffice);

    /**
     * 修改机构
     * 
     * @param tbSysOffice 机构
     * @return 结果
     */
    public int updateTbSysOffice(TbSysOffice tbSysOffice);

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
