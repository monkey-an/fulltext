package com.fulltext.project.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DocumentInfo {

    private Long id;


    private Long documentId;


    private Long documentTypeId;


    private String serialName;


    private String documentName;


    private String documentAuthor;


    private String documentYear;

    private Integer documentTotalPage;

    private String documentIsbn;


    private BigDecimal documentPrice;


    private String documentDesc;


    private String documentSummary;


    private String remark;


    private Byte status;


    private Date createTime;


    private Date updateTime;

}