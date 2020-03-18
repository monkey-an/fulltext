package com.fulltext.project.service.impl;

import com.fulltext.project.dao.DocumentDetailMapper;
import com.fulltext.project.entity.DocumentDetail;
import com.fulltext.project.service.DocumentDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class DocumentDetailServiceImpl implements DocumentDetailService {
    @Resource
    private DocumentDetailMapper documentDetailMapper;

    @Override
    public DocumentDetail selectDocumentDetailById(Long id) {
        return null;
    }

    @Override
    public List<DocumentDetail> selectDocumentDetailListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(DocumentDetail entity) {
        return documentDetailMapper.insert(entity);
    }

    @Override
    public int update(DocumentDetail entity) {
        return documentDetailMapper.updateByPrimaryKey(entity);
    }

    @Override
    public DocumentDetail selectDocumentDetailByDocumentAndMenuId(long documentId, long menuId) {
        return documentDetailMapper.selectDocumentDetailByDocumentAndMenuId(documentId,menuId);
    }

    @Override
    public DocumentDetail selectDocumentDetailByDocumentId(long documentId) {
        return documentDetailMapper.selectDocumentDetailByDocumentId(documentId);
    }
}
