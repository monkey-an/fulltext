package com.fulltext.project.dao;

import com.fulltext.project.entity.DocumentIdSeq;
import java.util.List;

public interface DocumentIdSeqMapper {
    
    int deleteByPrimaryKey(Long id);

    
    int insert(DocumentIdSeq record);

    
    List<DocumentIdSeq> selectAll();
}