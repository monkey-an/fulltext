package com.fulltext.project.util;

import com.fulltext.project.bo.PageBean;
import com.github.pagehelper.PageInfo;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/11.
 *
 * @author anlu.
 */
public class PageVoUtils {
    public static <T> PageBean<T> convertTopageVo(PageInfo<T> pageInfo){
        PageBean<T> pagevo=new PageBean<T>();
        pagevo.setTotal(pageInfo.getTotal());
        pagevo.setRows(pageInfo.getList());
        return pagevo;
    }
}
