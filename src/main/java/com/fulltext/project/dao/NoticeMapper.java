package com.fulltext.project.dao;

import com.fulltext.project.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Notice record);
    Notice selectByPrimaryKey(Long id);
    List<Notice> selectAll();
    int updateByPrimaryKey(Notice record);

    List<Notice> selectNoticeListByIdList(@Param("idList") List<Long> idList);

    List<Notice> selectNoticeInnerOneMonth();
}