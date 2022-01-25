package com.littlemoth.web.controller.system;


import com.littlemoth.common.core.controller.BaseController;
import com.littlemoth.common.core.domain.ResultData;
import com.littlemoth.common.core.domain.TbSysOffice;
import com.littlemoth.common.core.page.TableDataInfo;
import com.littlemoth.system.domain.convert.ConvertVo;
import com.littlemoth.system.service.ITbSysOfficeService;
import com.littlemoth.web.controller.req.TbSysOfficeEditReq;
import com.littlemoth.web.controller.req.TbSysOfficeReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构Controller
 *
 * @author xusan
 * @date 2022-01-12
 */
@RestController
@RequestMapping("/office")
public class TbSysOfficeController extends BaseController {
    @Autowired
    private ITbSysOfficeService tbSysOfficeService;

    /**
     * 查询机构列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbSysOfficeReq office) {
        startPage();
        List<TbSysOffice> list = tbSysOfficeService.selectTbSysOfficeList(office.reqToBean(),getUser());
        return getDataTable(ConvertVo.convertVoListTbSysOfficeVo(list));
    }


    /**
     * 获取机构详细信息
     */
    @GetMapping(value = "/id/{id}")
    public ResultData getInfo(@PathVariable("id") Long id) {
        TbSysOffice tbSysOffice = tbSysOfficeService.selectTbSysOfficeById(id);
        return ResultData.success(ConvertVo.convertVoTbSysOfficeVo(tbSysOffice));
    }

    /**
     * 新增机构
     */
    @PostMapping(value = "/add")
    public ResultData add(@RequestBody TbSysOfficeReq office) {
        return ResultData.success(tbSysOfficeService.insertTbSysOffice(office.reqToBean(),getUser()));
    }

    /**
     * 修改机构
     */
    @PostMapping(value = "/edit")
    public ResultData edit(@Validated @RequestBody TbSysOfficeEditReq office) {
        return ResultData.success(tbSysOfficeService.updateTbSysOffice(office.reqToBean(),getUser()));
    }

    /**
     * 删除机构
     */
    @DeleteMapping("/{ids}")
    public ResultData remove(@PathVariable Long[] ids) {
        return ResultData.success(tbSysOfficeService.deleteTbSysOfficeByIds(ids));
    }

    /**
     * 删除机构根据id
     */
    @GetMapping("/delete/{id}")
    public ResultData delete(@PathVariable Long id) {
        return ResultData.success(tbSysOfficeService.deleteTbSysOfficeById(id));
    }


    /**
     * 获取组织下拉树列表
     */
    @GetMapping("/treeselect")
    public ResultData remove(TbSysOfficeReq office) {

        List<TbSysOffice> list = tbSysOfficeService.selectTbSysOfficeList(office.reqToBean(),getUser());

        return ResultData.success(tbSysOfficeService.buildSysOfficeTreeSelect(list));
    }


}
