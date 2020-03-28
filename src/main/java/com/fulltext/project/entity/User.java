package com.fulltext.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
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