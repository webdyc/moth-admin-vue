package com.littlemoth.web.controller.system;

import com.littlemoth.common.core.controller.BaseController;
import com.littlemoth.common.core.domain.ResultData;
import com.littlemoth.common.core.page.TableDataInfo;
import com.littlemoth.system.domain.TbSysRole;
import com.littlemoth.system.domain.convert.ConvertVo;
import com.littlemoth.system.service.ITbSysRoleService;
import com.littlemoth.web.controller.req.TbSysRoleReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色Controller
 * 
 * @author xjl
 * @date 2022-01-19
 */
@RestController
@RequestMapping("/auth/role")
public class TbSysRoleController extends BaseController
{
    @Autowired
    private ITbSysRoleService tbSysRoleService;

    /**
     * 查询角色列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbSysRoleReq role)
    {
        startPage();
        List<TbSysRole> list = tbSysRoleService.selectTbSysRoleList(role.reqToBean());
        return getDataTable(ConvertVo.convertVoListTbSysRoleVo(list));
    }


    /**
     * 获取角色详细信息
     */
    @GetMapping(value = "/{id}")
    public ResultData getInfo(@PathVariable("id") Long id)
    {
        TbSysRole tbSysRole = tbSysRoleService.selectTbSysRoleById(id);
        return ResultData.success(ConvertVo.convertVoTbSysRoleVo(tbSysRole));
    }

    /**
     * 新增角色
     */
    @PostMapping
    public ResultData add(@RequestBody TbSysRoleReq role)
    {
        return ResultData.success(tbSysRoleService.insertTbSysRole(role.reqToBean()));
    }

    /**
     * 修改角色
     */
    @PutMapping
    public ResultData edit(@RequestBody TbSysRoleReq role)
    {
        return ResultData.success(tbSysRoleService.updateTbSysRole(role.reqToBean()));
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{ids}")
    public ResultData remove(@PathVariable Long[] ids)
    {
        return ResultData.success(tbSysRoleService.deleteTbSysRoleByIds(ids));
    }
}
