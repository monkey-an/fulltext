package com.fulltext.project.dao;

import com.fulltext.project.entity.NoticeFlow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeFlowMapper {
    int deleteByPrimaryKey(Long id);
    int insert(NoticeFlow record);
    NoticeFlow selectByPrimaryKey(Long id);
    List<NoticeFlow> selectAll();
    int updateByPrimaryKey(NoticeFlow record);

    List<NoticeFlow> selectNoticeFlowByTargetUserId(@Param("userId") Long userId);
}