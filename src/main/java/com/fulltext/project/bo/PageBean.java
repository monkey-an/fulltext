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
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPage;
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

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        if(total%pageSize==0){
            return (int)(total/pageSize);
        }else{
            return (int)(total/pageSize)+1;
        }
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
