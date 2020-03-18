package com.fulltext.project.service.impl;

import com.fulltext.project.dao.NoticeMapper;
import com.fulltext.project.entity.Notice;
import com.fulltext.project.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/18.
 */
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public Notice selectNoticeById(Long id) {
        return null;
    }

    @Override
    public List<Notice> selectNoticeListByIdList(List<Long> idList) {
        return noticeMapper.selectNoticeListByIdList(idList);
    }

    @Override
    public int insert(Notice entity) {
        return 0;
    }

    @Override
    public int update(Notice entity) {
        return 0;
    }

    @Override
    public List<Notice> selectNoticeInnerOneMonth() {
        return noticeMapper.selectNoticeInnerOneMonth();
    }
}
