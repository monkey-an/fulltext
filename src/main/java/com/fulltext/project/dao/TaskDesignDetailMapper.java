package com.fulltext.project.dao;

import com.fulltext.project.entity.TaskDesignDetail;
import java.util.List;

public interface TaskDesignDetailMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(TaskDesignDetail record);


    List<TaskDesignDetail> selectAll();
}