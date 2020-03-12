package com.fulltext.project.service;
import com.fulltext.project.entity.DocumentType;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface DocumentTypeService {
    DocumentType selectDocumentTypeById(Long id);
    List<DocumentType> selectDocumentTypeListByIdList(List<Long> idList);
    int insert(DocumentType entity);
    int update(DocumentType entity);
}