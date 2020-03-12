package com.fulltext.project.dao;

import com.fulltext.project.entity.DocumentType;
import java.util.List;

public interface DocumentTypeMapper {

    int deleteByPrimaryKey(Long id);


    int insert(DocumentType record);


    DocumentType selectByPrimaryKey(Long id);


    List<DocumentType> selectAll();


    int updateByPrimaryKey(DocumentType record);
}