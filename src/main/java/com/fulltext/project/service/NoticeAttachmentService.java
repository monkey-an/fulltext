package com.fulltext.project.service;
import com.fulltext.project.entity.NoticeAttachment;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/22.
 */
public interface NoticeAttachmentService {
    NoticeAttachment selectNoticeAttachmentById(Long id);
    int insert(NoticeAttachment entity);
    int update(NoticeAttachment entity);
    List<NoticeAttachment> selectNoticeAttachmentByNoticeId(Long noticeId);
}