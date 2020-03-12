package com.fulltext.project.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Payment {

    private Long id;


    private Long paymentId;


    private Long userId;


    private BigDecimal totalPrice;


    private BigDecimal actualPrice;


    private Date paymentTime;


    private Date createTime;


    private Date updateTime;
}