package com.littlemoth.system.domain.vo;

import lombok.Data;

import java.util.Date;


/**
 * <p> Description： 租户信息</p>
 *
 * @author chenpeilin 2020/6/18 11:00
 */
@Data
public class TenantModel {

    public TenantModel() {

    }

    public TenantModel(String tenantName, String contactName,
                       String contactPhone, String email,
                       Integer aothTenantId, String loginNo,
                       String tenantType, Integer aothUserId,
                       Date lastModified, Integer parentId
    ) {
        this.tenantName = tenantName;
        this.aothTenantId = aothTenantId;
        this.aothUserId = aothUserId;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.email = email;
        this.loginNo = loginNo;
        this.tenantType = tenantType;
        this.lastModified = lastModified;
        this.parentId = parentId;
    }

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 联系人
     */
    private String contactName;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * aoth租户 ID
     */
    private Integer aothTenantId;

    /**
     * auth对应的管理员的authUserId
     */
    private Integer aothUserId;

    /**
     * 租户类型json数组
     */
    private String tenantType;

    /**
     * 登录账号
     */
    private String loginNo;

    /**
     * 最后修改时间
     */
    private Date lastModified;

    /**
     * 父级租户ID
     */
    private Integer parentId;

}
