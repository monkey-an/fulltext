package com.fulltext.project.service.impl;

import com.fulltext.project.constants.ConstantValue;
import com.fulltext.project.dao.NoticeMapper;
import com.fulltext.project.entity.Notice;
import com.fulltext.project.entity.NoticeAttachment;
import com.fulltext.project.service.NoticeAttachmentService;
import com.fulltext.project.service.NoticeFlowService;
import com.fulltext.project.service.NoticeService;
import com.fulltext.project.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2020/03/18.
 */
@Service
@Slf4j
@Transactional
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    @Autowired
    private NoticeFlowService noticeFlowService;

    @Autowired
    private NoticeAttachmentService noticeAttachmentService;

    @Override
    public Notice selectNoticeById(Long id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Notice> selectNoticeListByIdList(List<Long> idList) {
        return noticeMapper.selectNoticeListByIdList(idList);
    }

    @Override
    public int insert(Notice entity) {
        return noticeMapper.insert(entity);
    }

    @Override
    public int update(Notice entity) {
        return noticeMapper.updateByPrimaryKey(entity);
    }

    @Override
    public List<Notice> selectNoticeInnerOneMonth() {
        return noticeMapper.selectNoticeInnerOneMonth();
    }

    @Override
    public String addNotice(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String noticeTitle = request.getParameter("notice-title-input");
        String ifInnerNotice = request.getParameter("if_inner_notice");
        String noticeAuthor = request.getParameter("notice-author-input");
        String content = request.getParameter("content").trim();
        String text = request.getParameter("text").trim();
        if(text.length()>50){
            text = text.substring(0,46)+"...";
        }

        Notice notice = Notice.builder()
                .noticeAuthor(noticeAuthor)
                .noticeDesc(text)
                .noticeContent(content)
                .noticeTitle(noticeTitle)
                .noticeType("true".equals(ifInnerNotice)?1:2)
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        insert(notice);

        Map<String, MultipartFile> attachmentMap = multipartRequest.getFileMap();
        String filePath = ConstantValue.NOTICE_ATTACHMENT_FILE_PATH;
        if(attachmentMap!=null && attachmentMap.size()>0){
            attachmentMap.forEach((key,file)->{
                if (file != null && file.getSize() > 0) {
                    String fileName = file.getOriginalFilename();
                    try {
                        FileUploadUtil.saveFile(file.getBytes(), filePath, fileName);
                        NoticeAttachment noticeAttachment = NoticeAttachment.builder()
                                .noticeId(notice.getId())
                                .attachmentName(fileName)
                                .attachmentUrl(filePath+fileName)
                                .createTime(new Date())
                                .updateTime(new Date())
                                .build();

                        noticeAttachmentService.insert(noticeAttachment);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
            });
        }
        return "SUCCESS";
    }
}
