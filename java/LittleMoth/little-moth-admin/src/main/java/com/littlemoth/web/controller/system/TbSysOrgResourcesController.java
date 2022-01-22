package com.littlemoth.web.controller.system;


import com.littlemoth.common.core.controller.BaseController;
import com.littlemoth.common.core.domain.ResultData;
import com.littlemoth.common.core.page.TableDataInfo;
import com.littlemoth.system.domain.TbSysOrgResources;
import com.littlemoth.system.domain.convert.ConvertVo;
import com.littlemoth.system.service.ITbSysOrgResourcesService;
import com.littlemoth.web.controller.req.TbSysOrgResourcesReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织资源 Controller
 *
 * @author xjl
 * @date 2022-01-20
 */
@RestController
@RequestMapping("/auth/resources")
public class TbSysOrgResourcesController extends BaseController {
    @Autowired
    private ITbSysOrgResourcesService tbSysOrgResourcesService;

    /**
     * 查询组织资源 列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbSysOrgResourcesReq resources) {
        startPage();
        List<TbSysOrgResources> list = tbSysOrgResourcesService.selectTbSysOrgResourcesList(resources.reqToBean());
        return getDataTable(ConvertVo.convertVoListTbSysOrgResourcesVo(list));
    }


    /**
     * 获取组织资源 详细信息
     */
    @GetMapping(value = "/{id}")
    public ResultData getInfo(@PathVariable("id") Long id) {
        TbSysOrgResources tbSysOrgResources = tbSysOrgResourcesService.selectTbSysOrgResourcesById(id);
        return ResultData.success(ConvertVo.convertVoTbSysOrgResourcesVo(tbSysOrgResources));
    }

    /**
     * 新增组织资源
     */
    @PostMapping
    public ResultData add(@RequestBody TbSysOrgResourcesReq resources) {
        return ResultData.success(tbSysOrgResourcesService.insertTbSysOrgResources(resources.reqToBean()));
    }

    /**
     * 修改组织资源
     */
    @PutMapping
    public ResultData edit(@RequestBody TbSysOrgResourcesReq resources) {
        return ResultData.success(tbSysOrgResourcesService.updateTbSysOrgResources(resources.reqToBean()));
    }

    /**
     * 删除组织资源
     */
    @DeleteMapping("/{ids}")
    public ResultData remove(@PathVariable Long[] ids) {
        return ResultData.success(tbSysOrgResourcesService.deleteTbSysOrgResourcesByIds(ids));
    }
}
