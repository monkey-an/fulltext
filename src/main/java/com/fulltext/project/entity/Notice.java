package com.fulltext.project.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Notice {
    private Long id;
    private String noticeTitle;
    private String noticeAuthor;
    private String noticeContent;
    private String noticeAttachmentNameList;
    private String noticeAttachmentDownloadPath;
    private Integer noticeType;
    private Long noticeDepartmentId;
    private Date createTime;
    private Date updateTime;

    public String getSummaryDesc(){
        if(noticeContent.length()>20){
            return noticeContent.substring(0,20);
        }
        return noticeContent;
    }
}