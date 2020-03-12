package com.fulltext.project.dao;

import com.fulltext.project.entity.WorkGroup;
import java.util.List;

public interface WorkGroupMapper {

    int deleteByPrimaryKey(Long id);


    int insert(WorkGroup record);


    WorkGroup selectByPrimaryKey(Long id);


    List<WorkGroup> selectAll();


    int updateByPrimaryKey(WorkGroup record);
}