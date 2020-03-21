package com.fulltext.project.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class User {

    private Long id;


    private String nickName;


    private Long companyId;


    private String companyName;


    private Long departmentId;


    private String departmentName;


    private String loginName;


    private String email;


    private String phone;


    private String realName;


    private String idCardNo;


    private String salt;


    private String password;


    private Byte status;


    private Date efficativeDate;


    private Date createTime;


    private Date updateTime;
}