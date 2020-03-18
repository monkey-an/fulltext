package com.fulltext.project.service;
import com.fulltext.project.entity.NoticeFlow;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/18.
 */
public interface NoticeFlowService {
    NoticeFlow selectNoticeFlowById(Long id);
    List<NoticeFlow> selectNoticeFlowListByIdList(List<Long> idList);
    int insert(NoticeFlow entity);
    int update(NoticeFlow entity);

    List<NoticeFlow> selectNoticeFlowByTargetUserId(Long id);
}