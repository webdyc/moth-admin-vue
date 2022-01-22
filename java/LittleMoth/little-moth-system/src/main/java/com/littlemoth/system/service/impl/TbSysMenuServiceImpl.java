package com.littlemoth.system.service.impl;

import com.littlemoth.common.constant.Constants;
import com.littlemoth.common.constant.UserConstants;
import com.littlemoth.common.core.domain.TreeSelect;
import com.littlemoth.common.core.domain.entity.SysMenu;
import com.littlemoth.common.core.domain.entity.TbSysMenu;
import com.littlemoth.common.core.domain.model.LoginUser;
import com.littlemoth.common.core.domain.model.TbSysUser;
import com.littlemoth.common.utils.DateUtils;
import com.littlemoth.common.utils.SecurityUtils;
import com.littlemoth.common.utils.StringUtils;
import com.littlemoth.system.domain.vo.TbMetaVo;
import com.littlemoth.system.domain.vo.TbRouterVo;
import com.littlemoth.system.mapper.TbSysMenuMapper;
import com.littlemoth.system.service.ITbSysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单Service业务层处理
 *
 * @author xusan
 * @date 2022-01-12
 */
@Service
public class TbSysMenuServiceImpl implements ITbSysMenuService {
    @Autowired
    private TbSysMenuMapper tbSysMenuMapper;

    /**
     * 查询菜单
     *
     * @param id 菜单主键
     * @return 菜单
     */
    @Override
    public TbSysMenu selectTbSysMenuById(Long id) {
        return tbSysMenuMapper.selectTbSysMenuById(id);
    }

    /**
     * 查询菜单列表
     *
     * @param tbSysMenu 菜单
     * @return 菜单
     */
    @Override
    public List<TbSysMenu> selectTbSysMenuList(TbSysMenu tbSysMenu) {
        tbSysMenu.setIsDel(Integer.parseInt(Constants.SUCCESS));
        return tbSysMenuMapper.selectTbSysMenuList(tbSysMenu);
    }

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    @Override
    public List<TbSysMenu> selectTbSysMenuList(TbSysMenu menu, TbSysUser user) {
        List<TbSysMenu> menuList = null;
        menu.setIsDel(Integer.parseInt(Constants.SUCCESS));
        // 管理员显示所有菜单信息
        if (TbSysUser.isAdmin(Long.parseLong(user.getUserType()))) {
            menuList = tbSysMenuMapper.selectTbSysMenuListByMenuType(menu);
        } else {
            menuList = tbSysMenuMapper.selectMenuTreeByUserId(user.getId());
        }
        return menuList;
    }

    /**
     * 新增菜单
     *
     * @param tbSysMenu 菜单
     * @return 结果
     */
    @Override
    public int insertTbSysMenu(TbSysMenu tbSysMenu) {
        tbSysMenu.setParentId(StringUtils.isNull(tbSysMenu.getParentId()) ? "0" : tbSysMenu.getParentId());
        return tbSysMenuMapper.insertTbSysMenu(tbSysMenu);
    }

    /**
     * 修改菜单
     *
     * @param tbSysMenu 菜单
     * @return 结果
     */
    @Override
    public int updateTbSysMenu(TbSysMenu tbSysMenu) {
        tbSysMenu.setUpdateTime(DateUtils.getNowDate());
        return tbSysMenuMapper.updateTbSysMenu(tbSysMenu);
    }


    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<TbSysMenu> buildMenuTree(List<TbSysMenu> menus) {
        List<TbSysMenu> returnList = new ArrayList<TbSysMenu>();
        List<Long> tempList = new ArrayList<Long>();
        for (TbSysMenu dept : menus) {
            tempList.add(dept.getId());
        }
        for (Iterator<TbSysMenu> iterator = menus.iterator(); iterator.hasNext(); ) {
            TbSysMenu menu = (TbSysMenu) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(Long.parseLong(menu.getParentId()))) {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty()) {
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
    public List<TreeSelect> buildMenuTreeSelect(List<TbSysMenu> menus) {
        List<TbSysMenu> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 批量删除菜单
     *
     * @param ids 需要删除的菜单主键
     * @return 结果
     */
    @Override
    public int deleteTbSysMenuByIds(Long[] ids) {
        return tbSysMenuMapper.deleteTbSysMenuByIds(ids);
    }

    /**
     * 删除菜单信息
     *
     * @param id 菜单主键
     * @return 结果
     */
    @Override
    public int deleteTbSysMenuById(Long id) {
        return tbSysMenuMapper.deleteTbSysMenuById(id);
    }

    /**
     * 校验菜单名称是否唯一
     *
     * @param tbSysMenu 菜单信息
     * @return 结果
     */
    @Override
    public String checkMenuNameUnique(TbSysMenu tbSysMenu) {
        String menuId = StringUtils.isNull(tbSysMenu.getParentId()) ? "0" : tbSysMenu.getParentId();
        TbSysMenu info = tbSysMenuMapper.checkMenuNameUnique(tbSysMenu.getName(), menuId);
        if (StringUtils.isNotNull(info) && info.getName().equals(tbSysMenu.getName())) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
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
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<TbRouterVo> buildMenus(List<TbSysMenu> menus, String[] target) {
        List<TbRouterVo> routers = new LinkedList<TbRouterVo>();
        for (TbSysMenu menu : menus) {
            TbRouterVo router = new TbRouterVo();
            router.setHidden(isHidden(menu));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new TbMetaVo(menu.getTitle(), menu.getIcon(),
                    getIsRouterShow(menu.getBreadcrumb()), getIsRouterShow(menu.getKeepAlive()), menu.getTargetArr()));
            List<TbSysMenu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
//                router.setAlwaysShow(true);
                router.setRedirect(menu.getRedirect() == null ? "" : menu.getRedirect());
                //按钮权限
                String[] targetRole = null;
                if (!cMenus.isEmpty() && cMenus.size() > 0) {
                    for (int i = 0; i < cMenus.size(); i++) {
                        TbSysMenu tbSysMenu = cMenus.get(i);
                        if (UserConstants.TYPE_MENU.equals(tbSysMenu.getMenuType())) {
                            List<TbSysMenu> children = tbSysMenu.getChildren();
                            targetRole = new String[children.size()];
                            if (!children.isEmpty() && children.size() > 0) {
                                for (int j = 0; j < children.size(); j++) {
                                    TbSysMenu tbSysMenu1 = children.get(j);
                                    targetRole[j] = tbSysMenu1.getTarget();
                                }
                            }
                        }
                    }
                }
                router.setChildren(buildMenus(cMenus, targetRole));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<TbRouterVo> childrenList = new ArrayList<TbRouterVo>();
                TbRouterVo children = new TbRouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new TbMetaVo(menu.getTitle(), menu.getIcon(),
                        getIsRouterShow(menu.getBreadcrumb()), getIsRouterShow(menu.getKeepAlive()), menu.getTargetArr()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    private boolean isHidden(TbSysMenu menu) {
        if (Objects.isNull(menu.getHidden())){
            return false;
        }
        return menu.getHidden().equals("1")?true:false;
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
            routerPath =  menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "";
        }
        return routerPath;
    }

    /**
     * 路由信息是否展示
     *
     * @param num 菜单信息
     * @return 路由地址
     */
    public Boolean getIsRouterShow(Integer num) {
        return num.equals(UserConstants.OFF) ? false : true;
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
    public boolean isInnerLink(SysMenu menu) {
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


    @Override
    public List<TbSysMenu> selectTbSysMenuListTree(List<TbSysMenu> rootlist) {

        // 最后的结果
        List<TbSysMenu> menuList = new ArrayList<TbSysMenu>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootlist.size(); i++) {
            // 一级菜单为0
            if (rootlist.get(i).getParentId().equals(Constants.STAIR_MENU)) {
                menuList.add(rootlist.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (TbSysMenu menu : menuList) {
            menu.setChildren(getChild(menu.getId(), rootlist));
        }
        return menuList;
    }


    /**
     * 递归查找子菜单
     *
     * @param id       当前菜单id
     * @param rootMenu 要查找的列表
     * @return
     */
    private List<TbSysMenu> getChild(Long id, List<TbSysMenu> rootMenu) {
        // 子菜单
        List<TbSysMenu> childList = new ArrayList<>();
        for (TbSysMenu menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StringUtils.isNotBlank(menu.getParentId())) {
                if (menu.getParentId().equals(String.valueOf(id))) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (TbSysMenu menu : childList) {// F是菜单
            if (menu.getMenuType().equals(Constants.MENU_TYPE_C)) {
                // 递归
                menu.setChildren(getChild(menu.getId(), rootMenu));
            }
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}
