package com.littlemoth.web.controller.system;

import com.littlemoth.common.constant.Constants;
import com.littlemoth.common.core.domain.AjaxResult;
import com.littlemoth.common.core.domain.ResultData;
import com.littlemoth.common.core.domain.entity.SysMenu;
import com.littlemoth.common.core.domain.entity.SysUser;
import com.littlemoth.common.core.domain.entity.TbSysMenu;
import com.littlemoth.common.core.domain.model.LoginBody;
import com.littlemoth.common.core.domain.model.LoginUser;
import com.littlemoth.common.core.domain.model.TbSysUser;
import com.littlemoth.common.utils.SecurityUtils;
import com.littlemoth.framework.web.service.SysLoginService;
import com.littlemoth.framework.web.service.SysPermissionService;
import com.littlemoth.system.service.ISysMenuService;
import com.littlemoth.system.service.ITbSysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.schema.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 * 
 * @author xiusan
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ITbSysMenuService iTbSysMenuService;


    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public ResultData login(@RequestBody LoginBody loginBody)
    {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        objectObjectHashMap.put(Constants.TOKEN, token);
        return ResultData.success(objectObjectHashMap);
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        TbSysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public ResultData getRouters()
    {
        //获取用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        List<TbSysMenu> menuList = iTbSysMenuService.selectMenuTreeByUserId(loginUser);
        return ResultData.success(iTbSysMenuService.buildMenus(menuList,null));

    }
}
