package com.fulltext.project.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DocumentStorage {

    private Long id;


    private Long documentId;


    private Integer elementType;


    private String elementPath;


    private Byte status;


    private Date createTime;


    private Date updateTime;
}