package com.littlemoth.system.mapper;

import com.littlemoth.system.domain.TbSysOrgResources;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 组织资源 Mapper接口
 * 
 * @author xjl
 * @date 2022-01-20
 */
@Repository
public interface TbSysOrgResourcesMapper 
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
     * 删除组织资源 
     * 
     * @param id 组织资源 主键
     * @return 结果
     */
    public int deleteTbSysOrgResourcesById(Long id);

    /**
     * 批量删除组织资源 
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbSysOrgResourcesByIds(Long[] ids);
}
