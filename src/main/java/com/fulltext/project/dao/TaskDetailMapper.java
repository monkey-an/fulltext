package com.fulltext.project.dao;

import com.fulltext.project.entity.TaskDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskDetailMapper {

    int deleteByPrimaryKey(Long id);


    int insert(TaskDetail record);


    TaskDetail selectByPrimaryKey(Long id);


    List<TaskDetail> selectAll();


    int updateByPrimaryKey(TaskDetail record);

    List<TaskDetail> selectTaskDetailByTaskId(@Param("taskId") Long taskId);

    List<TaskDetail> selectTaskDetailByTaskIdAndOperNodeNo(@Param("taskId") Long taskId, @Param("formNo")String formNo);

    List<Long> selectTaskIdByOperUserId(@Param("userId") Long userId);
}