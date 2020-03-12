package com.fulltext.project.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetail {

    private Long id;


    private Long orderId;


    private Long documentId;


    private Integer count;


    private BigDecimal totalPrice;


    private Date createTime;


    private Date updateTime;
}