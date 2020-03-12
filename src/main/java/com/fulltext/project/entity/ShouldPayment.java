package com.fulltext.project.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ShouldPayment {

    private Long id;


    private Long paymentId;


    private BigDecimal price;


    private Byte status;


    private Date createTime;


    private Date updateTime;
}