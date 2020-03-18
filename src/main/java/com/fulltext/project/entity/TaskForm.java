package com.fulltext.project.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TaskForm {
    private Long id;

    private String formNo;

    private String formContent;

    private Date createTime;

    private Date updateTime;
}