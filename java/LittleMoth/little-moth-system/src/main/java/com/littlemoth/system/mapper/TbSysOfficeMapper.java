package com.littlemoth.system.mapper;

import com.littlemoth.common.core.domain.TbSysOffice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 机构Mapper接口
 * 
 * @author xusan
 * @date 2022-01-12
 */
@Repository
public interface TbSysOfficeMapper
{
    /**
     * 查询机构
     *
     * @param id 机构主键
     * @return 机构
     */
    public TbSysOffice selectTbSysOfficeById(Long id);

    /**
     * 查询机构
     *
     * @param officeCode 机构主键
     * @return 机构
     */
    public TbSysOffice selectTbSysOfficeByOfficeCode(String officeCode);

    /**
     * 查询机构列表
     *
     * @param tbSysOffice 机构
     * @return 机构集合
     */
    public List<TbSysOffice> selectTbSysOfficeList(TbSysOffice tbSysOffice);

    /**
     * 新增机构
     *
     * @param tbSysOffice 机构
     * @return 结果
     */
    public int insertTbSysOffice(TbSysOffice tbSysOffice);

    /**
     * 修改机构
     *
     * @param tbSysOffice 机构
     * @return 结果
     */
    public int updateTbSysOffice(TbSysOffice tbSysOffice);

    /**
     * 删除机构
     *
     * @param id 机构主键
     * @return 结果
     */
    public int deleteTbSysOfficeById(Long id);

    /**
     * 批量删除机构
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbSysOfficeByIds(Long[] ids);

    /**
     * 查询该时间之后的数据
     * @param lastTime
     * @return
     */
    List<TbSysOffice> getOfficeList(@Param("lastTime") String lastTime);

}

