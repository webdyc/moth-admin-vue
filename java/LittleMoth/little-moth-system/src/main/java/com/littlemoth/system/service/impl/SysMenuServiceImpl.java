package com.littlemoth.system.service.impl;

import com.littlemoth.common.constant.Constants;
import com.littlemoth.common.constant.UserConstants;
import com.littlemoth.common.core.domain.TreeSelect;
import com.littlemoth.common.core.domain.entity.SysMenu;
import com.littlemoth.common.core.domain.entity.SysRole;
import com.littlemoth.common.core.domain.entity.SysUser;
import com.littlemoth.common.core.domain.entity.TbSysMenu;
import com.littlemoth.common.core.domain.model.LoginUser;
import com.littlemoth.common.core.domain.model.TbSysUser;
import com.littlemoth.common.utils.SecurityUtils;
import com.littlemoth.common.utils.StringUtils;
import com.littlemoth.system.domain.vo.TbMetaVo;
import com.littlemoth.system.domain.vo.TbRouterVo;
import com.littlemoth.system.mapper.SysMenuMapper;
import com.littlemoth.system.mapper.SysRoleMapper;
import com.littlemoth.system.mapper.SysRoleMenuMapper;
import com.littlemoth.system.mapper.TbSysMenuMapper;
import com.littlemoth.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单 业务层处理
 * 
 * @author xiusan
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService
{
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private SysMenuMapper menuMapper;

    @Autowired
    private TbSysMenuMapper tbSysMenuMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    /**
     * 根据用户查询系统菜单列表
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuList(Long userId)
    {
        return selectMenuList(new SysMenu(), userId);
    }

    /**
     * 查询系统菜单列表
     * 
     * @param menu 菜单信息
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuList(SysMenu menu, Long userId)
    {
        List<SysMenu> menuList = null;
        // 管理员显示所有菜单信息
        if (SysUser.isAdmin(userId))
        {
            menuList = menuMapper.selectMenuList(menu);
        }
        else
        {
            menu.getParams().put("userId", userId);
            menuList = menuMapper.selectMenuListByUserId(menu);
        }
        return menuList;
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId)
    {
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }


    /**
     * 根据用户ID查询菜单
     *
     * @param loginUser 用户名称
     * @return 菜单列表
     */
    @Override
    public List<TbSysMenu> selectMenuTreeByUserId(LoginUser loginUser) {
        TbSysUser user = loginUser.getUser();
        //获取类型
        Long userType = Long.parseLong(user.getUserType());
        List<TbSysMenu> menus = null;
        if (SecurityUtils.isAdmin(userType)) {
            menus = tbSysMenuMapper.selectMenuTreeAll();
        } else {
            menus = tbSysMenuMapper.selectMenuTreeByUserId(userType);
        }
        return getChildPerms(menus, "0");
    }
    /**
     * 根据角色ID查询菜单树信息
     * 
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<Long> selectMenuListByRoleId(Long roleId)
    {
        SysRole role = roleMapper.selectRoleById(roleId);
        return menuMapper.selectMenuListByRoleId(roleId, role.isMenuCheckStrictly());
    }



    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<TbRouterVo> buildMenus(List<TbSysMenu> menus,String[] target) {
        List<TbRouterVo> routers = new LinkedList<TbRouterVo>();
        for (TbSysMenu menu : menus) {
            TbRouterVo router = new TbRouterVo();
            router.setHidden(true);
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new TbMetaVo(menu.getName(), menu.getIcon(),
                    getIsRouterShow(menu.getBreadcrumb()), getIsRouterShow(menu.getKeepAlive()), menu.getTargetArr()));
            List<TbSysMenu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
//                router.setAlwaysShow(true);
                router.setRedirect(menu.getRedirect() == null ? "" : menu.getRedirect());
                //按钮权限
                String[] targetRole =null;
                if(!cMenus.isEmpty() && cMenus.size() > 0 ){
                    for (int i = 0; i < cMenus.size(); i++) {
                        TbSysMenu tbSysMenu = cMenus.get(i);
                        if(UserConstants.TYPE_MENU.equals(tbSysMenu.getMenuType())) {
                            List<TbSysMenu> children = tbSysMenu.getChildren();
                            targetRole = new String[children.size()];
                            if(!children.isEmpty() && children.size() > 0 ){
                                for (int j = 0; j < children.size(); j++) {
                                    TbSysMenu tbSysMenu1 = children.get(j);
                                    targetRole[j] = tbSysMenu1.getTarget();
                                }
                            }
                        }
                    }
                }
                router.setChildren(buildMenus(cMenus,targetRole));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<TbRouterVo> childrenList = new ArrayList<TbRouterVo>();
                TbRouterVo children = new TbRouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new TbMetaVo(menu.getName(), menu.getIcon(),
                        getIsRouterShow(menu.getBreadcrumb()), getIsRouterShow(menu.getKeepAlive()), menu.getTargetArr()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }


    /**
     * 路由信息是否展示
     *
     * @param num 菜单信息
     * @return 路由地址
     */
    public Boolean getIsRouterShow(Integer num) {
        return num.equals(UserConstants.OFF)?false:true;
    }
    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<TbSysMenu> buildMenuTree(List<TbSysMenu> menus)
    {
        List<TbSysMenu> returnList = new ArrayList<TbSysMenu>();
        List<Long> tempList = new ArrayList<Long>();
        for (TbSysMenu dept : menus)
        {
            tempList.add(dept.getId());
        }
        for (Iterator<TbSysMenu> iterator = menus.iterator(); iterator.hasNext();)
        {
            TbSysMenu menu = (TbSysMenu) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(Long.parseLong(menu.getParentId())))
            {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = menus;
        }
        return returnList;
    }


    /**
     * 构建前端所需要下拉树结构
     * 
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<TbSysMenu> menus)
    {
        List<TbSysMenu> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public SysMenu selectMenuById(Long menuId)
    {
        return menuMapper.selectMenuById(menuId);
    }

    /**
     * 是否存在菜单子节点
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean hasChildByMenuId(Long menuId)
    {
        int result = menuMapper.hasChildByMenuId(menuId);
        return result > 0 ? true : false;
    }

    /**
     * 查询菜单使用数量
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean checkMenuExistRole(Long menuId)
    {
        int result = roleMenuMapper.checkMenuExistRole(menuId);
        return result > 0 ? true : false;
    }

    /**
     * 新增保存菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(SysMenu menu)
    {
        return menuMapper.insertMenu(menu);
    }

    /**
     * 修改保存菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(SysMenu menu)
    {
        return menuMapper.updateMenu(menu);
    }

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long menuId)
    {
        return menuMapper.deleteMenuById(menuId);
    }

    /**
     * 校验菜单名称是否唯一
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public String checkMenuNameUnique(SysMenu menu)
    {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        SysMenu info = menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }


    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(TbSysMenu menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(TbSysMenu menu) {
        String routerPath = menu.getPath();
//        // 内链打开外网方式
//        if (menu.getParentId().intValue() != 0 && isInnerLink(menu))
//        {
//            routerPath = innerLinkReplaceEach(routerPath);
//        }
        // 非外链并且是一级目录（类型为目录）
        if ("0".equals(menu.getParentId()) && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }


    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(TbSysMenu menu) {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && !menu.getParentId().equals("0")) {
            component = UserConstants.INNER_LINK;
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(TbSysMenu menu) {
        return menu.getParentId().equals("0") && UserConstants.TYPE_MENU.equals(menu.getMenuType());
    }
    /**
     * 是否为内链组件
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(SysMenu menu)
    {
        return menu.getIsFrame().equals(UserConstants.NO_FRAME) && StringUtils.ishttp(menu.getPath());
    }


    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(TbSysMenu menu) {
        return !menu.getParentId().equals("0") && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<TbSysMenu> getChildPerms(List<TbSysMenu> list, String parentId) {
        List<TbSysMenu> returnList = new ArrayList<TbSysMenu>();
        for (Iterator<TbSysMenu> iterator = list.iterator(); iterator.hasNext(); ) {
            TbSysMenu t = (TbSysMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId().equals(parentId)) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }



    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<TbSysMenu> list, TbSysMenu t) {
        // 得到子节点列表
        List<TbSysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (TbSysMenu tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }


    /**
     * 得到子节点列表
     */
    private List<TbSysMenu> getChildList(List<TbSysMenu> list, TbSysMenu t) {
        List<TbSysMenu> tlist = new ArrayList<TbSysMenu>();
        Iterator<TbSysMenu> it = list.iterator();
        while (it.hasNext()) {
            TbSysMenu n = (TbSysMenu) it.next();
            if (n.getParentId().equals(String.valueOf(t.getId()))) {
                tlist.add(n);
            }
        }
        return tlist;
    }


    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<TbSysMenu> list, TbSysMenu t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }


    /**
     * 内链域名特殊字符替换
     * 
     * @return
     */
    public String innerLinkReplaceEach(String path)
    {
        return StringUtils.replaceEach(path, new String[] { Constants.HTTP, Constants.HTTPS },
                new String[] { "", "" });
    }
}
