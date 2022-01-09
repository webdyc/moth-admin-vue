package com.littlemoth.common.utils;

import com.github.pagehelper.PageHelper;
import com.littlemoth.common.utils.sql.SqlUtil;
import com.littlemoth.common.core.page.PageDomain;
import com.littlemoth.common.core.page.TableSupport;

/**
 * 分页工具类
 * 
 * @author xiusan
 */
public class PageUtils extends PageHelper
{
    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }
}
