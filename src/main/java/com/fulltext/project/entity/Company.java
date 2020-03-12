package com.fulltext.project.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Company {

    private Long id;


    private String companyName;


    private String leaderName;


    private Date createTime;


    private Date updateTime;

}