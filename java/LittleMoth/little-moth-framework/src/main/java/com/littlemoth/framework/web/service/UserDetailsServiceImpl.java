package com.littlemoth.framework.web.service;

import com.littlemoth.common.core.domain.model.LoginUser;
import com.littlemoth.common.core.domain.model.TbSysUser;
import com.littlemoth.common.enums.UserStatus;
import com.littlemoth.common.exception.ServiceException;
import com.littlemoth.common.utils.StringUtils;
import com.littlemoth.system.service.ITbSysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户验证处理
 *
 * @author xjl
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ITbSysUserService iTbSysUserService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbSysUser tbSysUserPojo = new TbSysUser();
        tbSysUserPojo.setUserName(username);

        List<TbSysUser> tbSysUserList = iTbSysUserService.selectTbSysUserList(tbSysUserPojo);
        if (StringUtils.isNull(tbSysUserList)) {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        }

        TbSysUser tbSysUser = tbSysUserList.get(0);
        if (UserStatus.DELETED.getCode().equals(tbSysUser.getIsDel())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }

        return createLoginUser(tbSysUser);
    }

    public UserDetails createLoginUser(TbSysUser user) {
        return new LoginUser(user.getId(), user.getCompanyId(), user, permissionService.getMenuPermission(null));
    }
}
