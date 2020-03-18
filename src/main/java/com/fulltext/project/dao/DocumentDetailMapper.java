package com.fulltext.project.dao;

import com.fulltext.project.entity.DocumentDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentDetailMapper {
    
    int deleteByPrimaryKey(Long id);

    
    int insert(DocumentDetail record);

    
    DocumentDetail selectByPrimaryKey(Long id);

    
    List<DocumentDetail> selectAll();

    
    int updateByPrimaryKey(DocumentDetail record);

    DocumentDetail selectDocumentDetailByDocumentAndMenuId(@Param("documentId") long documentId,@Param("menuId") long menuId);

    DocumentDetail selectDocumentDetailByDocumentId(@Param("documentId") long documentId);
}