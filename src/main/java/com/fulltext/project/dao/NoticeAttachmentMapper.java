package com.fulltext.project.dao;

import com.fulltext.project.entity.NoticeAttachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeAttachmentMapper {
    int insert(NoticeAttachment record);
    NoticeAttachment selectByPrimaryKey(Long id);
    List<NoticeAttachment> selectAll();
    int updateByPrimaryKey(NoticeAttachment record);
    List<NoticeAttachment> selectByNoticeId(@Param("noticeId")Long noticeId);
}