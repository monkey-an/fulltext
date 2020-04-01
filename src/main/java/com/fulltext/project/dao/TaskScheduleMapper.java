package com.fulltext.project.dao;

import com.fulltext.project.entity.TaskSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskScheduleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskSchedule record);

    TaskSchedule selectByPrimaryKey(Long id);

    List<TaskSchedule> selectAll();

    int updateByPrimaryKey(TaskSchedule record);

    List<TaskSchedule> selectByTaskName(@Param("taskName") String taskName);
}