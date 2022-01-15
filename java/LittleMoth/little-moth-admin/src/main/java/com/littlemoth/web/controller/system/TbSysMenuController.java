package com.littlemoth.web.controller.system;

import com.littlemoth.common.constant.UserConstants;
import com.littlemoth.common.core.controller.BaseController;
import com.littlemoth.common.core.domain.ResultData;
import com.littlemoth.common.core.page.TableDataInfo;
import com.littlemoth.system.domain.TbSysMenu;
import com.littlemoth.system.service.ITbSysMenuService;
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
    public ResultData add(@RequestBody TbSysMenu tbSysMenu)
    {
        if (UserConstants.NOT_UNIQUE.equals(tbSysMenuService.checkMenuNameUnique(tbSysMenu)))
        {
            return ResultData.fail("新增菜单'" + tbSysMenu.getName() + "'失败，菜单名称已存在");
        }

    tbSysMenu.setCreateBy(getUsername());
    return ResultData.success(tbSysMenuService.insertTbSysMenu(tbSysMenu));
    }

    /**
     * 查询菜单列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbSysMenu tbSysMenu)
    {
        startPage();
        List<TbSysMenu> list = tbSysMenuService.selectTbSysMenuList(tbSysMenu);
        return getDataTable(list);
    }


    /**
     * 获取菜单详细信息
     */
    @GetMapping(value = "/id/{id}")
    public ResultData getInfo(@PathVariable("id") Long id)
    {
        return ResultData.success(tbSysMenuService.selectTbSysMenuById(id));
    }



    /**
     * 修改菜单
     */
    @PostMapping("/edit")
    public ResultData edit(@RequestBody TbSysMenu tbSysMenu)
    {
        return ResultData.success(tbSysMenuService.updateTbSysMenu(tbSysMenu));
    }

    /**
     * 删除菜单
     */
    @GetMapping("/del/{ids}")
    public ResultData remove(@PathVariable Long[] ids)
    {
        return ResultData.success(tbSysMenuService.deleteTbSysMenuByIds(ids));
    }
}
