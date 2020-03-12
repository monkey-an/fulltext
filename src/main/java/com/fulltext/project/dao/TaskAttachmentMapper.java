package com.fulltext.project.dao;

import com.fulltext.project.entity.TaskAttachment;
import java.util.List;

public interface TaskAttachmentMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(TaskAttachment record);


    List<TaskAttachment> selectAll();
}