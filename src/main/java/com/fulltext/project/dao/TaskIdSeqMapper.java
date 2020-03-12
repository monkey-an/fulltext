package com.fulltext.project.dao;

import com.fulltext.project.entity.TaskIdSeq;
import java.util.List;

public interface TaskIdSeqMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(TaskIdSeq record);


    List<TaskIdSeq> selectAll();
}