package com.fulltext.project.service;
import com.fulltext.project.entity.TaskFormHtml;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/14.
 */
public interface TaskFormHtmlService {
    TaskFormHtml selectTaskFormHtmlById(Long id);
    int insert(TaskFormHtml entity);
    int update(TaskFormHtml entity);
    TaskFormHtml selectTaskFormHtmlByTaskIdAndFormNo(Long taskId,String formNo);

    List<TaskFormHtml> selectTaskFormHtmlByTaskId(Long taskId);

    PageInfo<TaskFormHtml> selectAllUserByPaging(int pageNo, int pageSize, String userName);
}