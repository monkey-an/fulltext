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
public class DocumentMenu {

    private Long id;


    private Long documentId;


    private String menuName;


    private String menuPage;


    private Long parentMenuId;


    private Date createTime;


    private Date updateTime;
}