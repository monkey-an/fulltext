package com.fulltext.project.dao;

import com.fulltext.project.entity.DocumentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentInfoMapper {
    
    int deleteByPrimaryKey(Long id);

    
    int insert(DocumentInfo record);

    
    DocumentInfo selectByPrimaryKey(Long id);

    
    List<DocumentInfo> selectAll(@Param("documentName") String documentName,@Param("year") String year);

    
    int updateByPrimaryKey(DocumentInfo record);

    List<DocumentInfo> selectBySerialNameList(@Param("serialNameList") List<String> serialNameList);

    DocumentInfo selectByDocumentId(@Param("documentId")Long documentId);

    List<DocumentInfo> selectDocumentInfoListByDocumentIdList(@Param("documentIdList") List<Long> documentIdList);
}