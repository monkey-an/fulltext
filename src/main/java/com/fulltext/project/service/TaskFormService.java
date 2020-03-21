package com.fulltext.project.service;
import com.fulltext.project.entity.TaskForm;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/16.
 */
public interface TaskFormService {
    TaskForm selectTaskFormByFormNo(String formNo);
}