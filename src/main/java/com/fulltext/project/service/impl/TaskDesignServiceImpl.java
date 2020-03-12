package com.fulltext.project.service.impl;

import com.fulltext.project.dao.TaskDesignMapper;
import com.fulltext.project.entity.TaskDesign;
import com.fulltext.project.service.TaskDesignService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class TaskDesignServiceImpl implements TaskDesignService {
    @Resource
    private TaskDesignMapper taskDesignMapper;

    @Override
    public TaskDesign selectTaskDesignById(Long id) {
        return null;
    }

    @Override
    public List<TaskDesign> selectTaskDesignListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(TaskDesign entity) {
        return 0;
    }

    @Override
    public int update(TaskDesign entity) {
        return 0;
    }
}
