package com.littlemoth.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构对象 tb_sys_officeVo
 *
 * @author xjl
 * @date 2022-01-12
 */
public class TbSysOfficeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;


    /**
     * 名称
     */
    private String name;

    /**
     * 上级组织名称
     */
    private String parentName;
    /**
     * 排序
     */
    private Long sort;

    /**
     * 备注信息
     */
    private String remarks;


    /**
     * 组织code
     */
    private String officeCode;
    /**
     * 组织code
     */
    private String useable;

    /**
     * 创建用户
     */
    private String userName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TbSysOfficeVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentName='" + parentName + '\'' +
                ", sort=" + sort +
                ", remarks='" + remarks + '\'' +
                ", officeCode='" + officeCode + '\'' +
                ", useable='" + useable + '\'' +
                ", userName=" + userName +
                ", createTime=" + createTime +
                '}';
    }
}
