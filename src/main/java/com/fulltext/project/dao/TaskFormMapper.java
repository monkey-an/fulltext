package com.fulltext.project.dao;

import com.fulltext.project.entity.TaskForm;
import java.util.List;

public interface TaskFormMapper {
    TaskForm selectTaskFormByFormNo(String formNo);
}