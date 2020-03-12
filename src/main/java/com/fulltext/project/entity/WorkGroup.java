package com.fulltext.project.entity;

import lombok.Data;

import java.util.Date;

@Data
public class WorkGroup {

    private Long id;


    private String groupName;


    private String groupDesc;


    private Date createTime;


    private Date updateTime;
}