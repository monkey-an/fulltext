package com.fulltext.project.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/19.
 *
 * @author anlu.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentInfo {
    private Long id;
    private Long documentId;
    private Long documentTypeId;
    private String serialName;
    private String documentName;
    private String documentAuthor;
    private String documentMembers;
    private String documentYear;
    private Integer documentTotalPage;
    private Integer documentTotalCount;
    private String documentPublisher;
    private String documentIsbn;
    private BigDecimal documentPrice;
    private String documentDesc;
    private String documentSummary;
    private String remark;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    public String getDocumentSummaryDesc(){
        if(documentSummary.length()>60){
            return documentSummary.substring(0,57)+"...";
        }
        return documentSummary;
    }
}
