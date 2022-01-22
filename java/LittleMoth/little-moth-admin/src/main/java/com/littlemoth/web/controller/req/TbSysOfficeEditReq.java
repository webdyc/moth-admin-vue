package com.littlemoth.web.controller.req;

import com.littlemoth.common.core.domain.TbSysOffice;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 机构对象 tb_sys_office
 *
 * @author xjl
 * @date 2022-01-12
 */
public class TbSysOfficeEditReq implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @NotNull(message = "组织ID不能为空")
    private Long id;


    /**
     * 名称
     */
    private String name;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 是否启用
     */
    private String useable;


    public TbSysOffice reqToBean() {
        TbSysOffice office = new TbSysOffice();
        office.setId(id);
        office.setName(name);
        office.setRemarks(remarks);
        office.setUseable(useable);
        return office;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
