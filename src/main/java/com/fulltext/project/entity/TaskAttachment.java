package com.fulltext.project.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TaskAttachment {

    private Long id;


    private Long taskId;


    private String attachmentName;


    private String path;


    private Date createTime;


    private Date updateTime;
}