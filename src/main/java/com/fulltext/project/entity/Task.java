package com.fulltext.project.entity;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@Builder
public class Task {
    private Long id;
    private Long taskId;
    private String taskName;
    private Long commitUserId;
    private String commitUserName;
    private String currentNodeNo;
    private String currentApprovalUserId;
    private String currentApprovalUserName;
    private String currentApprovalStatus;
    private Date createTime;
    private Date updateTime;

    public String getStatusDesc(){
        String result = "";
        switch (currentApprovalStatus){
            case "DONE":
                result = "完成";
                break;
            case "DOING":
                result = "进行中";
                break;
            case "REJECT":
                result = "驳回";
                break;
        }
        return result;
    }

    public String getCreateDateDesc(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(createTime);
    }
}