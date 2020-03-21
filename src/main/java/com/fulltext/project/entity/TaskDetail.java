package com.fulltext.project.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
public class TaskDetail {

    private Long id;


    private Long taskId;

    private String operNodeNo;


    private Long operUserId;


    private String operUserName;


    private String operType;


    private String operResult;


    private Date createTime;


    private Date updateTime;
}