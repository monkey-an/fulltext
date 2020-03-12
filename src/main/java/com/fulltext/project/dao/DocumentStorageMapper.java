package com.fulltext.project.dao;

import com.fulltext.project.entity.DocumentStorage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentStorageMapper {

    int deleteByPrimaryKey(Long id);


    int insert(DocumentStorage record);


    DocumentStorage selectByPrimaryKey(Long id);


    List<DocumentStorage> selectAll();


    int updateByPrimaryKey(DocumentStorage record);

    DocumentStorage selectOneByDocumentIdAndElementType(@Param("documentId") Long documentId,@Param("elementType") int elementType);

    DocumentStorage selectOneByDocumentIdAndMenuIdAndElementType(@Param("documentId")Long documentId, @Param("menuId")Long menuId, @Param("elementType")Integer elementType);
}