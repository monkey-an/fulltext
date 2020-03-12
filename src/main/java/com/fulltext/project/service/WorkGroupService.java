package com.fulltext.project.service;
import com.fulltext.project.entity.WorkGroup;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface WorkGroupService {
    WorkGroup selectWorkGroupById(Long id);
    List<WorkGroup> selectWorkGroupListByIdList(List<Long> idList);
    int insert(WorkGroup entity);
    int update(WorkGroup entity);
}