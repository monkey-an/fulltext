package com.fulltext.project.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DocumentType {

    private Long id;


    private String typeName;


    private Byte status;


    private Date createTime;


    private Date updateTime;
}