package com.fulltext.project.service.impl;

import com.fulltext.project.dao.DocumentTypeMapper;
import com.fulltext.project.entity.DocumentType;
import com.fulltext.project.service.DocumentTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class DocumentTypeServiceImpl implements DocumentTypeService {
    @Resource
    private DocumentTypeMapper documentTypeMapper;

    @Override
    public DocumentType selectDocumentTypeById(Long id) {
        return null;
    }

    @Override
    public List<DocumentType> selectDocumentTypeListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(DocumentType entity) {
        return 0;
    }

    @Override
    public int update(DocumentType entity) {
        return 0;
    }
}
