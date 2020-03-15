package com.fulltext.project.dao;

import com.fulltext.project.entity.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {

    int deleteByPrimaryKey(Long id);


    int insert(Task record);


    Task selectByPrimaryKey(Long id);


    List<Task> selectAll();


    int updateByPrimaryKey(Task record);

    Task selectTaskByTaskId(@Param("taskId") Long taskId);

    List<Task> selectByCommitUserId(@Param("userId") Long userId);

    List<Task> selectByCurrentUserId(@Param("userId")Long userId);
}