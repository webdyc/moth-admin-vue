package com.littlemoth.web.controller.system;

import com.littlemoth.common.core.controller.BaseController;
import com.littlemoth.common.core.domain.ResultData;
import com.littlemoth.common.core.page.TableDataInfo;
import com.littlemoth.system.domain.TbSysRoleOffice;
import com.littlemoth.system.domain.convert.ConvertVo;
import com.littlemoth.system.service.ITbSysRoleOfficeService;
import com.littlemoth.web.controller.req.TbSysRoleOfficeReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色-机构Controller
 *
 * @author xjl
 * @date 2022-01-20
 */
@RestController
@RequestMapping("/auth/roleOffice")
public class TbSysRoleOfficeController extends BaseController {
    @Autowired
    private ITbSysRoleOfficeService tbSysRoleOfficeService;

    /**
     * 查询角色-机构列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbSysRoleOfficeReq roleOffice) {
        startPage();
        List<TbSysRoleOffice> list = tbSysRoleOfficeService.selectTbSysRoleOfficeList(roleOffice.reqToBean());
        return getDataTable(ConvertVo.convertVoListTbSysRoleOfficeVo(list));
    }


    /**
     * 获取角色-机构详细信息
     */
    @GetMapping(value = "/{id}")
    public ResultData getInfo(@PathVariable("id") Long id) {
        TbSysRoleOffice tbSysRoleOffice = tbSysRoleOfficeService.selectTbSysRoleOfficeById(id);
        return ResultData.success(ConvertVo.convertVoTbSysRoleOfficeVo(tbSysRoleOffice));
    }

    /**
     * 新增角色-机构
     */
    @PostMapping
    public ResultData add(@RequestBody TbSysRoleOfficeReq roleOffice) {
        return ResultData.success(tbSysRoleOfficeService.insertTbSysRoleOffice(roleOffice.reqToBean()));
    }

    /**
     * 修改角色-机构
     */
    @PutMapping
    public ResultData edit(@RequestBody TbSysRoleOfficeReq roleOffice) {
        return ResultData.success(tbSysRoleOfficeService.updateTbSysRoleOffice(roleOffice.reqToBean()));
    }

    /**
     * 删除角色-机构
     */
    @DeleteMapping("/{ids}")
    public ResultData remove(@PathVariable Long[] ids) {
        return ResultData.success(tbSysRoleOfficeService.deleteTbSysRoleOfficeByIds(ids));
    }
}
