package com.fulltext.project.service;
import com.fulltext.project.bo.DocumentMenuNode;
import com.fulltext.project.bo.DocumentSearchBO;
import com.fulltext.project.bo.DocumentSerialBO;
import com.fulltext.project.bo.DocumentSerialDetailBO;
import com.fulltext.project.entity.DocumentInfo;
import com.fulltext.project.entity.DocumentMenu;
import com.fulltext.project.entity.User;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface DocumentInfoService {
    DocumentInfo selectDocumentInfoByDocumentId(Long documentId);
    List<DocumentInfo> selectDocumentInfoListByDocumentIdList(List<Long> docuemntIdList);
    int insert(DocumentInfo entity);
    int update(DocumentInfo entity);

    List<DocumentSerialBO> loadDefaultDocumentInfoBySerialName(List<String> serialNameList);
    List<DocumentSerialDetailBO> loadDocumentInfoBySerialName(String serialName);
    Map<String, List<DocumentMenu>> flatRootMenuToMap(List<DocumentMenuNode> menuNodeList);

    PageInfo<DocumentSearchBO> selectSearchDocumentByPaging(int pageNo, int pageSize, String documentName, String year);
    List<Map<String,Object>> getAllMenuNameMap();

    void createDocumentSearch(String documentMenuId,String content);

    PageInfo<DocumentInfo> selectUserSearchDocumentByPaging(int pageNo, int pageSize, String searchValue, String searchWords);

    boolean addDocument(HttpServletRequest request);

    boolean addMenu(HttpServletRequest request);
}