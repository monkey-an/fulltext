package com.fulltext.project.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DocumentDetail {

    private Long id;


    private Long documentId;


    private Long menuId;


    private String author;


    private String summary;


    private String keyWords;


    private Byte status;


    private Date createTime;


    private Date updateTime;

}