package com.fulltext.project.bo;

import java.io.Serializable;
import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/11.
 *
 * @author anlu.
 */
public class PageBean<T> implements Serializable {
    private Long total;//满足条件的总记录数
    private List<T> rows;//所请求的当前页的数据集合

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
