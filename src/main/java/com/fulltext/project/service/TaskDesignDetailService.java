package com.fulltext.project.service;
import com.fulltext.project.entity.TaskDesignDetail;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface TaskDesignDetailService {
    TaskDesignDetail selectTaskDesignDetailById(Long id);
    List<TaskDesignDetail> selectTaskDesignDetailListByIdList(List<Long> idList);
    int insert(TaskDesignDetail entity);
    int update(TaskDesignDetail entity);
}