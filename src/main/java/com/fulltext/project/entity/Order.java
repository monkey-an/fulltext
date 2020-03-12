package com.fulltext.project.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {

    private Long id;


    private Long orderId;


    private Long userId;


    private Long paymentId;


    private Date orderCommitTime;


    private Integer orderStatus;


    private BigDecimal orderPrice;


    private Date createTime;


    private Date updateTime;
}