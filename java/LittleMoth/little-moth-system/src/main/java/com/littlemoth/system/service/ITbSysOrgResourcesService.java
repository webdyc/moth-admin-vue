package com.littlemoth.system.service;

import com.littlemoth.system.domain.TbSysOrgResources;

import java.util.List;

/**
 * 组织资源 Service接口
 * 
 * @author xjl
 * @date 2022-01-20
 */
public interface ITbSysOrgResourcesService 
{
    /**
     * 查询组织资源 
     * 
     * @param id 组织资源 主键
     * @return 组织资源 
     */
    public TbSysOrgResources selectTbSysOrgResourcesById(Long id);

    /**
     * 查询组织资源 列表
     * 
     * @param tbSysOrgResources 组织资源 
     * @return 组织资源 集合
     */
    public List<TbSysOrgResources> selectTbSysOrgResourcesList(TbSysOrgResources tbSysOrgResources);

    /**
     * 新增组织资源 
     * 
     * @param tbSysOrgResources 组织资源 
     * @return 结果
     */
    public int insertTbSysOrgResources(TbSysOrgResources tbSysOrgResources);

    /**
     * 修改组织资源 
     * 
     * @param tbSysOrgResources 组织资源 
     * @return 结果
     */
    public int updateTbSysOrgResources(TbSysOrgResources tbSysOrgResources);

    /**
     * 批量删除组织资源 
     * 
     * @param ids 需要删除的组织资源 主键集合
     * @return 结果
     */
    public int deleteTbSysOrgResourcesByIds(Long[] ids);

    /**
     * 删除组织资源 信息
     * 
     * @param id 组织资源 主键
     * @return 结果
     */
    public int deleteTbSysOrgResourcesById(Long id);
}
