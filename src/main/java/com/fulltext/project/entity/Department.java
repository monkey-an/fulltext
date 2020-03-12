package com.fulltext.project.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Department {

    private Long id;


    private Long companyId;


    private String departmentName;


    private Long leaderUserId;


    private String leaderName;


    private Long parentDepartmentId;


    private Date createTime;


    private Date updateTime;
}