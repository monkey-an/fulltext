package com.fulltext.project.dao;
import com.fulltext.project.entity.TaskFormHtml;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface TaskFormHtmlMapper {
    int deleteByPrimaryKey(Long id);
    int insert(TaskFormHtml record);
    TaskFormHtml selectByPrimaryKey(Long id);
    List<TaskFormHtml> selectAll();
    int updateByPrimaryKey(TaskFormHtml record);
    TaskFormHtml selectByTaskIdAndFormNo(@Param("taskId") Long taskId,@Param("formNo") String formNo);

    List<TaskFormHtml> selectTaskFormHtmlByTaskId(@Param("taskId") Long taskId);
}