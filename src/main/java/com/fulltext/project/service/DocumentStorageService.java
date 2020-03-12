package com.fulltext.project.service;
import com.fulltext.project.constants.ElementTypeEnum;
import com.fulltext.project.entity.DocumentStorage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface DocumentStorageService {
    DocumentStorage selectDocumentStorageById(Long id);
    List<DocumentStorage> selectDocumentStorageListByIdList(List<Long> idList);
    int insert(DocumentStorage entity);
    int update(DocumentStorage entity);

    DocumentStorage getDocumentStorageByDocumentIdAndElementType(Long documentId, Integer elementType);
    DocumentStorage getDocumentStorageByDocumentIdAndMenuIdAndElementType(Long documentId, Long menuId, Integer elementType);

    void downLoadDocument(String documentIdStr, String menuIdStr, String fileType, HttpServletResponse response) throws IOException;
}