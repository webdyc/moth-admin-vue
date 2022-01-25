package com.littlemoth.web.controller.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.littlemoth.common.core.domain.model.TbSysUser;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户对象 tb_sys_user
 * 
 * @author xjl
 * @date 2022-01-12
 */
public class TbSysUserReq  implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 归属公司 */
    private String companyId;

    /** 归属部门 */
    private Long officeId;

    /** 密码 */
    private String password;

    /** 工号 */
    private String no;

    /** 姓名 */
    private String userName;

    /** 用户昵称 */
    private String nickName;

    /** 邮箱 */
    private String email;

    /** 电话 */
    private String phone;

    /** 手机 */
    private String mobile;

    /** 用户类型 */
    private String userType;

    /** 用户头像 */
    private String photo;

    /** 最后登陆IP */
    private String loginIp;

    /** 最后登陆时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loginDate;

    /** 是否可登录 */
    private String loginFlag;

    /** 是否发送fs 默认0  0发送  1未发送  */
    private String fsFlag;

    /** 创建者 */
    private Long creator;

    /** 更新者 */
    private Long updateUser;

    /** 过期时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expirationTime;

    /** 备注信息 */
    private String remarks;

    /** 删除标记 默认0  是否删除  0否  1是  */
    private Integer isDel;


    public TbSysUser reqToBean() {
        TbSysUser user = new TbSysUser();
        user.setId(id);
        user.setCompanyId(companyId);
        user.setOfficeId(officeId);
        user.setPassword(password);
        user.setNo(no);
        user.setUserName(userName);
        user.setNickName(nickName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setMobile(mobile);
        user.setUserType(userType);
        user.setPhoto(photo);
        user.setLoginIp(loginIp);
        user.setLoginDate(loginDate);
        user.setLoginFlag(loginFlag);
        user.setFsFlag(fsFlag);
        user.setCreator(creator);
        user.setUpdateUser(updateUser);
        user.setExpirationTime(expirationTime);
        user.setRemarks(remarks);
        user.setIsDel(isDel);
        return user;
    }


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCompanyId(String companyId) 
    {
        this.companyId = companyId;
    }

    public String getCompanyId() 
    {
        return companyId;
    }
    public void setOfficeId(Long officeId)
    {
        this.officeId = officeId;
    }

    public Long getOfficeId()
    {
        return officeId;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setNo(String no) 
    {
        this.no = no;
    }

    public String getNo() 
    {
        return no;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setUserType(String userType) 
    {
        this.userType = userType;
    }

    public String getUserType() 
    {
        return userType;
    }
    public void setPhoto(String photo) 
    {
        this.photo = photo;
    }

    public String getPhoto() 
    {
        return photo;
    }
    public void setLoginIp(String loginIp) 
    {
        this.loginIp = loginIp;
    }

    public String getLoginIp() 
    {
        return loginIp;
    }
    public void setLoginDate(Date loginDate) 
    {
        this.loginDate = loginDate;
    }

    public Date getLoginDate() 
    {
        return loginDate;
    }
    public void setLoginFlag(String loginFlag) 
    {
        this.loginFlag = loginFlag;
    }

    public String getLoginFlag() 
    {
        return loginFlag;
    }
    public void setFsFlag(String fsFlag) 
    {
        this.fsFlag = fsFlag;
    }

    public String getFsFlag() 
    {
        return fsFlag;
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
    public void setExpirationTime(Date expirationTime) 
    {
        this.expirationTime = expirationTime;
    }

    public Date getExpirationTime() 
    {
        return expirationTime;
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

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 0L == userId;
    }

}
