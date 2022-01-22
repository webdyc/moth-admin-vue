package com.littlemoth.web.controller.system;

import com.littlemoth.common.core.controller.BaseController;
import com.littlemoth.common.core.domain.ResultData;
import com.littlemoth.common.core.page.TableDataInfo;
import com.littlemoth.system.domain.TbSysRoleMenu;
import com.littlemoth.system.domain.convert.ConvertVo;
import com.littlemoth.system.service.ITbSysRoleMenuService;
import com.littlemoth.web.controller.req.TbSysRoleMenuReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色-菜单Controller
 *
 * @author xjl
 * @date 2022-01-20
 */
@RestController
@RequestMapping("/auth/roleMenu")
public class TbSysRoleMenuController extends BaseController {
    @Autowired
    private ITbSysRoleMenuService tbSysRoleMenuService;

    /**
     * 查询角色-菜单列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbSysRoleMenuReq roleMenu) {
        startPage();
        List<TbSysRoleMenu> list = tbSysRoleMenuService.selectTbSysRoleMenuList(roleMenu.reqToBean());
        return getDataTable(ConvertVo.convertVoListTbSysRoleMenuVo(list));
    }


    /**
     * 获取角色-菜单详细信息
     */
    @GetMapping(value = "/{id}")
    public ResultData getInfo(@PathVariable("id") Long id) {
        TbSysRoleMenu tbSysRoleMenu = tbSysRoleMenuService.selectTbSysRoleMenuById(id);
        return ResultData.success(ConvertVo.convertVoTbSysRoleMenuVo(tbSysRoleMenu));
    }

    /**
     * 新增角色-菜单
     */
    @PostMapping
    public ResultData add(@RequestBody TbSysRoleMenuReq roleMenu) {
        return ResultData.success(tbSysRoleMenuService.insertTbSysRoleMenu(roleMenu.reqToBean()));
    }

    /**
     * 修改角色-菜单
     */
    @PutMapping
    public ResultData edit(@RequestBody TbSysRoleMenuReq roleMenu) {
        return ResultData.success(tbSysRoleMenuService.updateTbSysRoleMenu(roleMenu.reqToBean()));
    }

    /**
     * 删除角色-菜单
     */
    @DeleteMapping("/{ids}")
    public ResultData remove(@PathVariable Long[] ids) {
        return ResultData.success(tbSysRoleMenuService.deleteTbSysRoleMenuByIds(ids));
    }
}
