package com.littlemoth.web.controller.req;

import com.littlemoth.common.core.domain.TbSysOffice;
import com.littlemoth.common.core.domain.model.TbSysUser;

import java.io.Serializable;

/**
 * 机构对象 tb_sys_office
 *
 * @author xjl
 * @date 2022-01-12
 */
public class TbSysOfficeReq implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 父级编号
     */
    private String parentId;

    /**
     * 所有父级编号
     */
    private String parentIds;

    /**
     * 名称
     */
    private String name;

    /**
     * 父名称
     */
    private String parentName;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 归属区域
     */
    private String areaId;

    /**
     * 区域编码
     */
    private String code;

    /**
     * 机构类型
     */
    private String type;

    /**
     * 机构等级
     */
    private String grade;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 负责人
     */
    private String master;

    /**
     * 电话
     */
    private String phone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否启用
     */
    private String useable;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 更新者
     */
    private Long updateUser;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记 默认0  是否删除  0否  1是
     */
    private Integer isDel;
    /**
     * 组织code
     */
    private String officeCode;


    public TbSysOffice reqToBean() {
        TbSysOffice office = new TbSysOffice();
        office.setId(id);
        office.setParentId(parentId);
        office.setParentIds(parentIds);
        office.setName(name);
        office.setParentName(parentName);
        office.setOfficeCode(officeCode);
        office.setSort(sort);
        office.setAreaId(areaId);
        office.setCode(code);
        office.setType(type);
        office.setGrade(grade);
        office.setAddress(address);
        office.setZipCode(zipCode);
        office.setMaster(master);
        office.setPhone(phone);
        office.setFax(fax);
        office.setEmail(email);
        office.setUseable(useable);
        office.setRemarks(remarks);
        office.setIsDel(isDel);
        return office;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return sort;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getMaster() {
        return master;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFax() {
        return fax;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }

    public String getUseable() {
        return useable;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getCreator() {
        return creator;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getIsDel() {
        return isDel;
    }


}
