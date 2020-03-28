package com.fulltext.project.service.impl;

import com.fulltext.project.dao.NoticeAttachmentMapper;
import com.fulltext.project.entity.NoticeAttachment;
import com.fulltext.project.service.NoticeAttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/22.
 */
@Service
@Transactional
public class NoticeAttachmentServiceImpl implements NoticeAttachmentService {
    @Resource
    private NoticeAttachmentMapper noticeAttachmentMapper;

    @Override
    public NoticeAttachment selectNoticeAttachmentById(Long id) {
        return noticeAttachmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(NoticeAttachment entity) {
        return noticeAttachmentMapper.insert(entity);
    }

    @Override
    public int update(NoticeAttachment entity) {
        return noticeAttachmentMapper.updateByPrimaryKey(entity);
    }

    @Override
    public List<NoticeAttachment> selectNoticeAttachmentByNoticeId(Long noticeId) {
        return noticeAttachmentMapper.selectByNoticeId(noticeId);
    }
}
