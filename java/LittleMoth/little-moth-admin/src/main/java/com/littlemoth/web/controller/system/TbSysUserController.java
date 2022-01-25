package com.littlemoth.web.controller.system;

import com.littlemoth.common.core.controller.BaseController;
import com.littlemoth.common.core.domain.ResultData;
import com.littlemoth.common.core.domain.model.TbSysUser;
import com.littlemoth.common.core.page.TableDataInfo;
import com.littlemoth.system.domain.convert.ConvertVo;
import com.littlemoth.system.service.ITbSysUserService;
import com.littlemoth.web.controller.req.TbSysUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户Controller
 * 
 * @author xjl
 * @date 2022-01-20
 */
@RestController
@RequestMapping("/auth/user")
public class TbSysUserController extends BaseController
{
    @Autowired
    private ITbSysUserService tbSysUserService;

    /**
     * 查询用户列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TbSysUserReq user)
    {
        startPage();
        List<TbSysUser> list = tbSysUserService.selectTbSysUserList(user.reqToBean());
        return getDataTable(ConvertVo.convertVoListTbSysUserVo(list));
    }


    /**
     * 获取用户详细信息
     */
    @GetMapping(value = "/{id}")
    public ResultData getInfo(@PathVariable("id") Long id)
    {
        TbSysUser tbSysUser = tbSysUserService.selectTbSysUserById(id);
        return ResultData.success(ConvertVo.convertVoTbSysUserVo(tbSysUser));
    }

    /**
     * 新增用户
     */
    @PostMapping
    public ResultData add(@RequestBody TbSysUserReq user)
    {
        return ResultData.success(tbSysUserService.insertTbSysUser(user.reqToBean()));
    }

    /**
     * 修改用户
     */
    @PutMapping
    public ResultData edit(@RequestBody TbSysUserReq user)
    {
        return ResultData.success(tbSysUserService.updateTbSysUser(user.reqToBean()));
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{ids}")
    public ResultData remove(@PathVariable Long[] ids)
    {
        return ResultData.success(tbSysUserService.deleteTbSysUserByIds(ids));
    }
}
