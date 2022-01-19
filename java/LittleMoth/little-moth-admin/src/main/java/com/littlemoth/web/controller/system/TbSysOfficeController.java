package com.littlemoth.web.controller.system;


import com.littlemoth.common.core.controller.BaseController;
import com.littlemoth.common.core.domain.ResultData;
import com.littlemoth.common.core.domain.TbSysOffice;
import com.littlemoth.common.core.page.TableDataInfo;
import com.littlemoth.system.service.ITbSysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构Controller
 *
 * @author xusan
 * @date 2022-01-12
 */
@RestController
@RequestMapping("/unioncall/office")
public class TbSysOfficeController extends BaseController {
    @Autowired
    private ITbSysOfficeService tbSysOfficeService;

    /**
     * 查询机构列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbSysOffice tbSysOffice) {
        startPage();
        List<TbSysOffice> list = tbSysOfficeService.selectTbSysOfficeList(tbSysOffice);
        return getDataTable(list);
    }


    /**
     * 获取机构详细信息
     */
    @GetMapping(value = "/id/{id}")
    public ResultData getInfo(@PathVariable("id") Long id) {
        return ResultData.success(tbSysOfficeService.selectTbSysOfficeById(id));
    }

    /**
     * 新增机构
     */
    @PostMapping
    public ResultData add(@RequestBody TbSysOffice tbSysOffice) {
        return ResultData.success(tbSysOfficeService.insertTbSysOffice(tbSysOffice));
    }

    /**
     * 修改机构
     */
    @PutMapping
    public ResultData edit(@RequestBody TbSysOffice tbSysOffice) {
        return ResultData.success(tbSysOfficeService.updateTbSysOffice(tbSysOffice));
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
     * 删除机构
     */
    @GetMapping("/treeselect")
    public ResultData remove(TbSysOffice tbSysOffice) {

        List<TbSysOffice> list = tbSysOfficeService.selectTbSysOfficeList(tbSysOffice);

        return ResultData.success(tbSysOfficeService.buildSysOfficeTreeSelect(list));
    }


}
