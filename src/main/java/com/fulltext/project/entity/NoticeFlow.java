package com.fulltext.project.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class NoticeFlow {

    private Long id;
    private Long targetUserId;
    private Long noticeId;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}