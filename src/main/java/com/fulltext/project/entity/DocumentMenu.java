package com.fulltext.project.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DocumentMenu {

    private Long id;


    private Long documentId;


    private String menuName;


    private String menuPage;


    private Long parentMenuId;


    private Date createTime;


    private Date updateTime;
}