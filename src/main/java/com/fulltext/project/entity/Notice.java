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
    private String noticeDesc;
    private String noticeContent;
    private Integer noticeType;
    private Long noticeDepartmentId;
    private Date createTime;
    private Date updateTime;
}