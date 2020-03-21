package com.fulltext.project.service;
import com.fulltext.project.entity.Notice;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/18.
 */
public interface NoticeService {
    Notice selectNoticeById(Long id);
    List<Notice> selectNoticeListByIdList(List<Long> idList);
    int insert(Notice entity);
    int update(Notice entity);

    List<Notice> selectNoticeInnerOneMonth();
}