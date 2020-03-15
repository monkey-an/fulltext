package com.fulltext.project.entity;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
@Data
@Builder
public class TaskFormHtml {
    private Long id;
    private Long taskId;
    private String formNo;
    private String formContent;
    private Date createTime;
    private Date updateTime;

    public String getFormNoDesc(){
        String[] arr = formNo.split("-");
        return arr[arr.length-1];
    }
}