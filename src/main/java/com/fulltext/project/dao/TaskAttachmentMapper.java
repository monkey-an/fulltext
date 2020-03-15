package com.fulltext.project.dao;

import com.fulltext.project.entity.TaskAttachment;
import java.util.List;

public interface TaskAttachmentMapper {

    int deleteByPrimaryKey(Long id);


    int insert(TaskAttachment record);


    TaskAttachment selectByPrimaryKey(Long id);


    List<TaskAttachment> selectAll();


    int updateByPrimaryKey(TaskAttachment record);
}