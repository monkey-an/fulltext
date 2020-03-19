package com.fulltext.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDetail {

    private Long id;


    private Long documentId;


    private Long menuId;


    private String author;


    private String summary;


    private String keyWords;


    private Integer status;


    private String completionUnit;

    private String members;


    private Date createTime;


    private Date updateTime;

}