package com.fulltext.project.dao;

import com.fulltext.project.entity.DocumentDetail;
import java.util.List;

public interface DocumentDetailMapper {
    
    int deleteByPrimaryKey(Long id);

    
    int insert(DocumentDetail record);

    
    DocumentDetail selectByPrimaryKey(Long id);

    
    List<DocumentDetail> selectAll();

    
    int updateByPrimaryKey(DocumentDetail record);
}