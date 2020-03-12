package com.fulltext.project.service;
import com.fulltext.project.entity.DocumentIdSeq;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface DocumentIdSeqService {
    DocumentIdSeq selectDocumentIdSeqById(Long id);
    List<DocumentIdSeq> selectDocumentIdSeqListByIdList(List<Long> idList);
    int insert(DocumentIdSeq entity);
    int update(DocumentIdSeq entity);
}