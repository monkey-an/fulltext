package com.fulltext.project.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Role {

    private Long id;


    private String roleName;


    private String roleDesc;


    private Long parentRoleId;


    private Integer status;


    private Date createTime;


    private Date updateTime;
}