package com.fulltext.project.service;
import com.fulltext.project.bo.DocumentMenuNode;
import com.fulltext.project.entity.DocumentMenu;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface DocumentMenuService {
    DocumentMenu selectDocumentMenuById(Long id);
    List<DocumentMenu> selectDocumentMenuListByIdList(List<Long> idList);
    int insert(DocumentMenu entity);
    int update(DocumentMenu entity);

    List<DocumentMenuNode> getDocumentMenuListByDocumentId(Long documentId);
}