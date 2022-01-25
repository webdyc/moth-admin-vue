package com.littlemoth.system.domain.convert;

import com.littlemoth.common.core.domain.TbSysOffice;
import com.littlemoth.common.core.domain.entity.TbSysMenu;
import com.littlemoth.common.core.domain.model.TbSysUser;
import com.littlemoth.system.domain.TbSysOrgResources;
import com.littlemoth.system.domain.TbSysRole;
import com.littlemoth.system.domain.TbSysRoleMenu;
import com.littlemoth.system.domain.TbSysRoleOffice;
import com.littlemoth.system.domain.vo.*;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName
 * @Author xjl
 * @Date 2022/01/20:18:15
 * @Description 转换为Vo
 * @Version 1.0.0
 */
public class ConvertVo {

    /**
     * 菜单
     * 转换为ListTbSysMenuVo
     */
    public static List<TbSysMenuVo> convertVoListTbSysMenuVo(List<TbSysMenu> list) {
        List<TbSysMenuVo> tbSysMenuVoList = null;
        if (Objects.nonNull(list)) {
            tbSysMenuVoList = Lists.newArrayList();
            for (TbSysMenu tbSysMenu : list) {
                TbSysMenuVo tbSysMenuVo = convertVoTbSysMenuVo(tbSysMenu);
                tbSysMenuVoList.add(tbSysMenuVo);
            }
        }
        return tbSysMenuVoList;
    }

    /**
     * 菜单
     * 转换为TbSysMenuVo
     */
    public static TbSysMenuVo convertVoTbSysMenuVo(TbSysMenu tbSysMenu) {
        TbSysMenuVo tbSysMenuVo = null;
        if (Objects.nonNull(tbSysMenu)) {
            tbSysMenuVo = new TbSysMenuVo();
            BeanUtils.copyProperties(tbSysMenu, tbSysMenuVo);
        }
        return tbSysMenuVo;
    }

    /**
     * 机构
     * 转换为ListTbSysOfficeVo
     */
    public static List<TbSysOfficeVo> convertVoListTbSysOfficeVo(List<TbSysOffice> list) {
        List<TbSysOfficeVo> tbSysOfficeList = null;
        if (Objects.nonNull(list)) {
            tbSysOfficeList = Lists.newArrayList();
            for (TbSysOffice tbSysOffice : list) {
                TbSysOfficeVo tbSysOfficeVo = convertVoTbSysOfficeVo(tbSysOffice);
                tbSysOfficeList.add(tbSysOfficeVo);
            }
        }
        return tbSysOfficeList;
    }

    /**
     * 机构
     * 转换为TbSysOfficeVo
     */
    public static TbSysOfficeVo convertVoTbSysOfficeVo(TbSysOffice TbSysOfficeVo) {
        TbSysOfficeVo tbSysOfficeVo = null;
        if (Objects.nonNull(TbSysOfficeVo)) {
            tbSysOfficeVo = new TbSysOfficeVo();
            BeanUtils.copyProperties(TbSysOfficeVo, tbSysOfficeVo);
        }
        return tbSysOfficeVo;
    }

    /**
     * 角色
     * 转换为ListTbSysRoleVo
     */
    public static List<TbSysRoleVo> convertVoListTbSysRoleVo(List<TbSysRole> list) {
        List<TbSysRoleVo> tbSysRoleVoList = null;
        if (Objects.nonNull(list)) {
            tbSysRoleVoList = Lists.newArrayList();
            for (TbSysRole tbSysRole : list) {
                TbSysRoleVo tbSysRoleVo = convertVoTbSysRoleVo(tbSysRole);
                tbSysRoleVoList.add(tbSysRoleVo);
            }
        }
        return tbSysRoleVoList;
    }

    /**
     * 角色
     * 转换为TbSysRoleVo
     */
    public static TbSysRoleVo convertVoTbSysRoleVo(TbSysRole role) {
        TbSysRoleVo tbSysRoleVo = null;
        if (Objects.nonNull(role)) {
            tbSysRoleVo = new TbSysRoleVo();
            BeanUtils.copyProperties(role, tbSysRoleVo);
        }
        return tbSysRoleVo;
    }

    /**
     * 用户
     * 转换为ListTbSysUserVo
     */
    public static List<TbSysUserVo> convertVoListTbSysUserVo(List<TbSysUser> list) {
        List<TbSysUserVo> tbSysUserVoList = null;
        if (Objects.nonNull(list)) {
            tbSysUserVoList = Lists.newArrayList();
            for (TbSysUser tbSysUser : list) {
                TbSysUserVo tbSysUserVo = convertVoTbSysUserVo(tbSysUser);
                tbSysUserVoList.add(tbSysUserVo);
            }
        }
        return tbSysUserVoList;
    }

    /**
     * 用户
     * 转换为TbTbSysUserVo
     */
    public static TbSysUserVo convertVoTbSysUserVo(TbSysUser user) {
        TbSysUserVo tbSysUserVo = null;
        if (Objects.nonNull(user)) {
            tbSysUserVo = new TbSysUserVo();
            BeanUtils.copyProperties(user, tbSysUserVo);
        }
        return tbSysUserVo;
    }

    /**
     * 资源
     * 转换为ListTbSysOrgResourcesVo
     */
    public static List<TbSysOrgResourcesVo> convertVoListTbSysOrgResourcesVo(List<TbSysOrgResources> list) {
        List<TbSysOrgResourcesVo> tbSysOrgResourcesVoList = null;
        if (Objects.nonNull(list)) {
            tbSysOrgResourcesVoList = Lists.newArrayList();
            for (TbSysOrgResources resources : list) {
                TbSysOrgResourcesVo tbSysOrgResourcesVo = convertVoTbSysOrgResourcesVo(resources);
                tbSysOrgResourcesVoList.add(tbSysOrgResourcesVo);
            }
        }
        return tbSysOrgResourcesVoList;
    }

    /**
     * 资源
     * 转换为TbSysOrgResourcesVo
     */
    public static TbSysOrgResourcesVo convertVoTbSysOrgResourcesVo(TbSysOrgResources resources) {
        TbSysOrgResourcesVo tbSysOrgResourcesVo = null;
        if (Objects.nonNull(resources)) {
            tbSysOrgResourcesVo = new TbSysOrgResourcesVo();
            BeanUtils.copyProperties(resources, tbSysOrgResourcesVo);
        }
        return tbSysOrgResourcesVo;
    }

    /**
     * 角色-菜单对象
     * 转换为ListTbSysRoleMenuVo
     */
    public static List<TbSysRoleMenuVo> convertVoListTbSysRoleMenuVo(List<TbSysRoleMenu> list) {
        List<TbSysRoleMenuVo> tbSysRoleMenuVoList = null;
        if (Objects.nonNull(list)) {
            tbSysRoleMenuVoList = Lists.newArrayList();
            for (TbSysRoleMenu roleMenuVo : list) {
                TbSysRoleMenuVo tbSysRoleMenuVo = convertVoTbSysRoleMenuVo(roleMenuVo);
                tbSysRoleMenuVoList.add(tbSysRoleMenuVo);
            }
        }
        return tbSysRoleMenuVoList;
    }

    /**
     * 资源
     * 转换为TbSysOrgResourcesVo
     */
    public static TbSysRoleMenuVo convertVoTbSysRoleMenuVo(TbSysRoleMenu tbSysRoleMenu) {
        TbSysRoleMenuVo tbSysRoleMenuVo = null;
        if (Objects.nonNull(tbSysRoleMenu)) {
            tbSysRoleMenuVo = new TbSysRoleMenuVo();
            BeanUtils.copyProperties(tbSysRoleMenu, tbSysRoleMenuVo);
        }
        return tbSysRoleMenuVo;
    }

    /**
     * 资源
     * 转换为ListTbSysRoleOfficeVo
     */
    public static List<TbSysRoleOfficeVo> convertVoListTbSysRoleOfficeVo(List<TbSysRoleOffice> list) {
        List<TbSysRoleOfficeVo> tbSysRoleOfficeVoList = null;
        if (Objects.nonNull(list)) {
            tbSysRoleOfficeVoList = Lists.newArrayList();
            for (TbSysRoleOffice sysRoleOfficeVo : list) {
                TbSysRoleOfficeVo tbSysRoleOfficeVo = convertVoTbSysRoleOfficeVo(sysRoleOfficeVo);
                tbSysRoleOfficeVoList.add(tbSysRoleOfficeVo);
            }
        }
        return tbSysRoleOfficeVoList;
    }

    /**
     * 资源
     * 转换为TbSysOrgResourcesVo
     */
    public static TbSysRoleOfficeVo convertVoTbSysRoleOfficeVo(TbSysRoleOffice sysRoleOfficeVo) {
        TbSysRoleOfficeVo tbSysRoleOfficeVo = null;
        if (Objects.nonNull(sysRoleOfficeVo)) {
            tbSysRoleOfficeVo = new TbSysRoleOfficeVo();
            BeanUtils.copyProperties(sysRoleOfficeVo, tbSysRoleOfficeVo);
        }
        return tbSysRoleOfficeVo;
    }

}
