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

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getDocumentId() {
//        return documentId;
//    }
//
//    public void setDocumentId(Long documentId) {
//        this.documentId = documentId;
//    }
//
//    public Long getDocumentTypeId() {
//        return documentTypeId;
//    }
//
//    public void setDocumentTypeId(Long documentTypeId) {
//        this.documentTypeId = documentTypeId;
//    }
//
//    public String getSerialName() {
//        return serialName;
//    }
//
//    public void setSerialName(String serialName) {
//        this.serialName = serialName;
//    }
//
//    public String getDocumentName() {
//        return documentName;
//    }
//
//    public void setDocumentName(String documentName) {
//        this.documentName = documentName;
//    }
//
//    public String getDocumentAuthor() {
//        return documentAuthor;
//    }
//
//    public void setDocumentAuthor(String documentAuthor) {
//        this.documentAuthor = documentAuthor;
//    }
//
//    public String getDocumentMembers() {
//        return documentMembers;
//    }
//
//    public void setDocumentMembers(String documentMembers) {
//        this.documentMembers = documentMembers;
//    }
//
//    public String getDocumentYear() {
//        return documentYear;
//    }
//
//    public void setDocumentYear(String documentYear) {
//        this.documentYear = documentYear;
//    }
//
//    public Integer getDocumentTotalPage() {
//        return documentTotalPage;
//    }
//
//    public void setDocumentTotalPage(Integer documentTotalPage) {
//        this.documentTotalPage = documentTotalPage;
//    }
//
//    public Integer getDocumentTotalCount() {
//        return documentTotalCount;
//    }
//
//    public void setDocumentTotalCount(Integer documentTotalCount) {
//        this.documentTotalCount = documentTotalCount;
//    }
//
//    public String getDocumentPublisher() {
//        return documentPublisher;
//    }
//
//    public void setDocumentPublisher(String documentPublisher) {
//        this.documentPublisher = documentPublisher;
//    }
//
//    public String getDocumentIsbn() {
//        return documentIsbn;
//    }
//
//    public void setDocumentIsbn(String documentIsbn) {
//        this.documentIsbn = documentIsbn;
//    }
//
//    public BigDecimal getDocumentPrice() {
//        return documentPrice;
//    }
//
//    public void setDocumentPrice(BigDecimal documentPrice) {
//        this.documentPrice = documentPrice;
//    }
//
//    public String getDocumentDesc() {
//        return documentDesc;
//    }
//
//    public void setDocumentDesc(String documentDesc) {
//        this.documentDesc = documentDesc;
//    }
//
//    public String getDocumentSummary() {
//        return documentSummary;
//    }
//
//    public void setDocumentSummary(String documentSummary) {
//        this.documentSummary = documentSummary;
//    }
//
//    public String getRemark() {
//        return remark;
//    }
//
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
}
