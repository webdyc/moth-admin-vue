package com.littlemoth.system.domain.vo;

import lombok.Data;

import java.io.Serializable;


/**
 * <p> Description： 租户信息</p>
 *
 * @author chenpeilin 2020/6/18 11:00
 */
@Data
public class TenantUserModel implements Serializable {

    /**
     * 用户ID
     */
    private Integer authUserId;

    /**
     * 租户ID
     */
    private Integer tenantId;

    /**
     * 用户是否没有冻结
     */
    private Boolean accountNonLocked;

    /**
     * 用户是否可用
     */
    private Boolean enabled;

    /**
     * 最后修改时间
     */
    private String lastModified;

    /**
     * 用户名真实名称
     */
    private String realName;

    /**
     * 用户名真实名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

}
