package com.fulltext.project.service.impl.workflow;

import com.fulltext.project.dao.TaskDesignDetailMapper;
import com.fulltext.project.entity.TaskDesignDetail;
import com.fulltext.project.service.TaskDesignDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class TaskDesignDetailServiceImpl implements TaskDesignDetailService {
    @Resource
    private TaskDesignDetailMapper taskDesignDetailMapper;

    @Override
    public TaskDesignDetail selectTaskDesignDetailById(Long id) {
        return null;
    }

    @Override
    public List<TaskDesignDetail> selectTaskDesignDetailListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(TaskDesignDetail entity) {
        return 0;
    }

    @Override
    public int update(TaskDesignDetail entity) {
        return 0;
    }
}
