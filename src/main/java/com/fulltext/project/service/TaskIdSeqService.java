package com.fulltext.project.service;
import com.fulltext.project.entity.TaskIdSeq;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface TaskIdSeqService {
    TaskIdSeq selectTaskIdSeqById(Long id);
    List<TaskIdSeq> selectTaskIdSeqListByIdList(List<Long> idList);
    int insert(TaskIdSeq entity);
    int update(TaskIdSeq entity);
}