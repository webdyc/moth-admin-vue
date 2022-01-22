package com.littlemoth.web.controller.system;

import com.littlemoth.common.core.controller.BaseController;
import com.littlemoth.common.core.domain.ResultData;
import com.littlemoth.common.core.domain.entity.TbSysMenu;
import com.littlemoth.common.core.page.TableDataInfo;
import com.littlemoth.system.domain.convert.ConvertVo;
import com.littlemoth.system.service.ITbSysMenuService;
import com.littlemoth.web.controller.req.TbSysMenuReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单Controller
 * 
 * @author xjl
 * @date 2022-01-13
 */
@RestController
@RequestMapping("/menu")
public class TbSysMenuController extends BaseController
{
    @Autowired
    private ITbSysMenuService tbSysMenuService;

    /**
     * 新增菜单
     */
    @PostMapping("/add")
    public ResultData add(@RequestBody TbSysMenuReq menu) {
//        if (UserConstants.NOT_UNIQUE.equals(tbSysMenuService.checkMenuNameUnique(tbSysMenu))) {
//            return ResultData.fail("新增菜单'" + tbSysMenu.getName() + "'失败，菜单名称已存在");
//        }

        menu.setCreator(getUserId());
        return ResultData.success(tbSysMenuService.insertTbSysMenu(menu.reqToBean()));
    }

    /**
     * 查询菜单列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbSysMenu tbSysMenu) {
        startPage();
        List<TbSysMenu> list = tbSysMenuService.selectTbSysMenuList(tbSysMenu);
        return getDataTable(ConvertVo.convertVoListTbSysMenuVo(list));
    }

    /**
     * 查询菜单列表
     */
    @GetMapping("/listTree")
    public ResultData listTree(TbSysMenu tbSysMenu) {
        List<TbSysMenu> list = tbSysMenuService.selectTbSysMenuList(tbSysMenu);
        List<TbSysMenu> listTree = tbSysMenuService.selectTbSysMenuListTree(list);
        return ResultData.success(ConvertVo.convertVoListTbSysMenuVo(listTree));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public ResultData treeselect(TbSysMenu menu) {
        List<TbSysMenu> menus = tbSysMenuService.selectTbSysMenuList(menu, getLoginUser().getUser());
        return ResultData.success(tbSysMenuService.buildMenuTreeSelect(menus));
    }

    /**
     * 获取菜单详细信息
     */
    @GetMapping(value = "/id/{id}")
    public ResultData getInfo(@PathVariable("id") Long id) {
        return ResultData.success(ConvertVo.convertVoTbSysMenuVo(tbSysMenuService.selectTbSysMenuById(id)));
    }


    /**
     * 修改菜单
     */
    @PostMapping("/edit")
    public ResultData edit(@RequestBody TbSysMenu tbSysMenu) {
        return ResultData.success(tbSysMenuService.updateTbSysMenu(tbSysMenu));
    }

    /**
     * 删除菜单 逻辑删除
     */
    @GetMapping("/delete/{id}")
    public ResultData delete(@PathVariable Long id) {
        return ResultData.success(tbSysMenuService.deleteTbSysMenuById(id));
    }

    /**
     * 删除菜单
     */
    @GetMapping("/del/{ids}")
    public ResultData remove(@PathVariable Long[] ids) {
        return ResultData.success(tbSysMenuService.deleteTbSysMenuByIds(ids));
    }
}
