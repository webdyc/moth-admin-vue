package com.littlemoth.system.service.impl;


import com.littlemoth.common.utils.DateUtils;
import com.littlemoth.system.domain.TbSysOrgResources;
import com.littlemoth.system.mapper.TbSysOrgResourcesMapper;
import com.littlemoth.system.service.ITbSysOrgResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组织资源 Service业务层处理
 * 
 * @author xjl
 * @date 2022-01-20
 */
@Service
public class TbSysOrgResourcesServiceImpl implements ITbSysOrgResourcesService
{
    @Autowired
    private TbSysOrgResourcesMapper tbSysOrgResourcesMapper;

    /**
     * 查询组织资源 
     * 
     * @param id 组织资源 主键
     * @return 组织资源 
     */
    @Override
    public TbSysOrgResources selectTbSysOrgResourcesById(Long id)
    {
        return tbSysOrgResourcesMapper.selectTbSysOrgResourcesById(id);
    }

    /**
     * 查询组织资源 列表
     * 
     * @param tbSysOrgResources 组织资源 
     * @return 组织资源 
     */
    @Override
    public List<TbSysOrgResources> selectTbSysOrgResourcesList(TbSysOrgResources tbSysOrgResources)
    {
        return tbSysOrgResourcesMapper.selectTbSysOrgResourcesList(tbSysOrgResources);
    }

    /**
     * 新增组织资源 
     * 
     * @param tbSysOrgResources 组织资源 
     * @return 结果
     */
    @Override
    public int insertTbSysOrgResources(TbSysOrgResources tbSysOrgResources)
    {
        tbSysOrgResources.setCreateTime(DateUtils.getNowDate());
        return tbSysOrgResourcesMapper.insertTbSysOrgResources(tbSysOrgResources);
    }

    /**
     * 修改组织资源 
     * 
     * @param tbSysOrgResources 组织资源 
     * @return 结果
     */
    @Override
    public int updateTbSysOrgResources(TbSysOrgResources tbSysOrgResources)
    {
        tbSysOrgResources.setUpdateTime(DateUtils.getNowDate());
        return tbSysOrgResourcesMapper.updateTbSysOrgResources(tbSysOrgResources);
    }

    /**
     * 批量删除组织资源 
     * 
     * @param ids 需要删除的组织资源 主键
     * @return 结果
     */
    @Override
    public int deleteTbSysOrgResourcesByIds(Long[] ids)
    {
        return tbSysOrgResourcesMapper.deleteTbSysOrgResourcesByIds(ids);
    }

    /**
     * 删除组织资源 信息
     * 
     * @param id 组织资源 主键
     * @return 结果
     */
    @Override
    public int deleteTbSysOrgResourcesById(Long id)
    {
        return tbSysOrgResourcesMapper.deleteTbSysOrgResourcesById(id);
    }
}
