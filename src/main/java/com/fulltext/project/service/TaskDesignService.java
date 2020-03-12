package com.fulltext.project.service;
import com.fulltext.project.entity.TaskDesign;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface TaskDesignService {
    TaskDesign selectTaskDesignById(Long id);
    List<TaskDesign> selectTaskDesignListByIdList(List<Long> idList);
    int insert(TaskDesign entity);
    int update(TaskDesign entity);
}