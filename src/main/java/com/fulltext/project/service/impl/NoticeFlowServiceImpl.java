package com.fulltext.project.service.impl;

import com.fulltext.project.dao.NoticeFlowMapper;
import com.fulltext.project.entity.NoticeFlow;
import com.fulltext.project.service.NoticeFlowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/18.
 */
@Service
@Transactional
public class NoticeFlowServiceImpl implements NoticeFlowService {
    @Resource
    private NoticeFlowMapper noticeFlowMapper;

    @Override
    public NoticeFlow selectNoticeFlowById(Long id) {
        return null;
    }

    @Override
    public List<NoticeFlow> selectNoticeFlowListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(NoticeFlow entity) {
        return 0;
    }

    @Override
    public int update(NoticeFlow entity) {
        return 0;
    }

    @Override
    public List<NoticeFlow> selectNoticeFlowByTargetUserId(Long userId) {
        return noticeFlowMapper.selectNoticeFlowByTargetUserId(userId);
    }
}
