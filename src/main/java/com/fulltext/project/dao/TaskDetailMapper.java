package com.fulltext.project.dao;

import com.fulltext.project.entity.TaskDetail;
import java.util.List;

public interface TaskDetailMapper {

    int deleteByPrimaryKey(Long id);


    int insert(TaskDetail record);


    TaskDetail selectByPrimaryKey(Long id);


    List<TaskDetail> selectAll();


    int updateByPrimaryKey(TaskDetail record);
}