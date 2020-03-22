package com.fulltext.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeAttachment {
    private Long id;

    private Long noticeId;

    private String attachmentName;

    private String attachmentUrl;

    private Date createTime;

    private Date updateTime;
}