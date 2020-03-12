package com.fulltext.project.dao;

import com.fulltext.project.entity.DocumentMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentMenuMapper {
    
    int deleteByPrimaryKey(Long id);

    
    int insert(DocumentMenu record);

    
    DocumentMenu selectByPrimaryKey(Long id);

    
    List<DocumentMenu> selectAll();

    
    int updateByPrimaryKey(DocumentMenu record);

    List<DocumentMenu> selectByDocumentId(@Param("documentId") Long documentId);
}