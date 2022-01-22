package com.littlemoth.web.controller.req;

import com.littlemoth.system.domain.TbSysOrgResources;

import java.io.Serializable;

/**
 * 组织资源 对象 tb_sys_org_resources
 * 
 * @author xjl
 * @date 2022-01-20
 */
public class TbSysOrgResourcesReq  implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 坐席数 */
    private Long agentCount;

    /** 并发数 */
    private Long concurrentCount;

    /** 质检数量 */
    private Long qtCount;

    /** 描述 */
    private String description;

    /** 创建者 */
    private Long creator;

    /** 更建者 */
    private Long updateUser;

    /** 备注信息 */
    private String remarks;

    /** 删除标记 默认0  是否删除  0否  1是  */
    private Integer isDel;

    public TbSysOrgResources reqToBean() {
        TbSysOrgResources resources = new TbSysOrgResources();
        resources.setId(id);
        resources.setAgentCount(agentCount);
        resources.setConcurrentCount(concurrentCount);
        resources.setQtCount(qtCount);
        resources.setDescription(description);
        resources.setCreator(creator);
        resources.setUpdateUser(updateUser);
        resources.setRemarks(remarks);
        resources.setIsDel(isDel);
        return resources;
    }
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAgentCount(Long agentCount) 
    {
        this.agentCount = agentCount;
    }

    public Long getAgentCount() 
    {
        return agentCount;
    }
    public void setConcurrentCount(Long concurrentCount) 
    {
        this.concurrentCount = concurrentCount;
    }

    public Long getConcurrentCount() 
    {
        return concurrentCount;
    }
    public void setQtCount(Long qtCount) 
    {
        this.qtCount = qtCount;
    }

    public Long getQtCount() 
    {
        return qtCount;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setCreator(Long creator) 
    {
        this.creator = creator;
    }

    public Long getCreator() 
    {
        return creator;
    }
    public void setUpdateUser(Long updateUser) 
    {
        this.updateUser = updateUser;
    }

    public Long getUpdateUser() 
    {
        return updateUser;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setIsDel(Integer isDel) 
    {
        this.isDel = isDel;
    }

    public Integer getIsDel() 
    {
        return isDel;
    }


}
