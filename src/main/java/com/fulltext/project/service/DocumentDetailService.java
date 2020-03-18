package com.fulltext.project.service;
import com.fulltext.project.entity.DocumentDetail;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface DocumentDetailService {
    DocumentDetail selectDocumentDetailById(Long id);
    List<DocumentDetail> selectDocumentDetailListByIdList(List<Long> idList);
    int insert(DocumentDetail entity);
    int update(DocumentDetail entity);

    DocumentDetail selectDocumentDetailByDocumentAndMenuId(long parseLong, long parseLong1);

    DocumentDetail selectDocumentDetailByDocumentId(long parseLong);
}