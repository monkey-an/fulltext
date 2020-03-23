package com.fulltext.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDetail {

    private Long id;
    private Long documentId;
    private Long menuId;
    private String completionUnit;
    private String author;
    private String members;
    private String summary;
    private String keyWords;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    private String documentPublisher;
    private String documentName;
    private String documentYear;

    private String documentMenuName;

    public String getPublisherDesc(){
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotEmpty(author)){
            sb.append(author+";&nbsp;&nbsp&nbsp&nbsp;&nbsp;&nbsp&nbsp&nbsp;");
        }
        if(StringUtils.isNotEmpty(members)){
            sb.append(members+";&nbsp;&nbsp&nbsp&nbsp;&nbsp;&nbsp&nbsp&nbsp;");
        }
        if(StringUtils.isNotEmpty(documentPublisher)){
            sb.append(documentPublisher+";&nbsp;&nbsp&nbsp&nbsp;&nbsp;&nbsp&nbsp&nbsp;");
        }
        if(StringUtils.isNotEmpty(completionUnit)){
            sb.append(completionUnit+";&nbsp;&nbsp&nbsp&nbsp;&nbsp;&nbsp&nbsp&nbsp;");
        }

        if(sb.length()>250){
            return sb.toString().substring(0,247)+"...";
        }else{
            return sb.toString();
        }
    }

    public String getSummaryDesc(){
        if(summary.length()>100){
            return summary.substring(0,97)+"...";
        }else{
            return summary;
        }
    }

}