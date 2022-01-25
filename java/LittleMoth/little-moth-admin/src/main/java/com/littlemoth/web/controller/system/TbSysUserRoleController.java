package com.littlemoth.web.controller.system;

import com.littlemoth.common.core.controller.BaseController;
import com.littlemoth.common.core.domain.ResultData;
import com.littlemoth.common.core.page.TableDataInfo;
import com.littlemoth.system.domain.TbSysUserRole;
import com.littlemoth.system.service.ITbSysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户-组织-角色Controller
 * 
 * @author xjl
 * @date 2022-01-20
 */
@RestController
@RequestMapping("/auth/userRole")
public class TbSysUserRoleController extends BaseController
{
    @Autowired
    private ITbSysUserRoleService tbSysUserRoleService;

    /**
     * 查询用户-组织-角色列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbSysUserRole tbSysUserRole)
    {
        startPage();
        List<TbSysUserRole> list = tbSysUserRoleService.selectTbSysUserRoleList(tbSysUserRole);
        return getDataTable(list);
    }


    /**
     * 获取用户-组织-角色详细信息
     */
    @GetMapping(value = "/{id}")
    public ResultData getInfo(@PathVariable("id") Long id)
    {
        return ResultData.success(tbSysUserRoleService.selectTbSysUserRoleById(id));
    }

    /**
     * 新增用户-组织-角色
     */
    @PostMapping
    public ResultData add(@RequestBody TbSysUserRole tbSysUserRole)
    {
        return ResultData.success(tbSysUserRoleService.insertTbSysUserRole(tbSysUserRole));
    }

    /**
     * 修改用户-组织-角色
     */
    @PutMapping
    public ResultData edit(@RequestBody TbSysUserRole tbSysUserRole)
    {
        return ResultData.success(tbSysUserRoleService.updateTbSysUserRole(tbSysUserRole));
    }

    /**
     * 删除用户-组织-角色
     */
    @DeleteMapping("/{ids}")
    public ResultData remove(@PathVariable Long[] ids)
    {
        return ResultData.success(tbSysUserRoleService.deleteTbSysUserRoleByIds(ids));
    }
}
