package com.fulltext.project.service.impl;

import com.fulltext.project.dao.DocumentIdSeqMapper;
import com.fulltext.project.entity.DocumentIdSeq;
import com.fulltext.project.service.DocumentIdSeqService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class DocumentIdSeqServiceImpl implements DocumentIdSeqService {
    @Resource
    private DocumentIdSeqMapper documentIdSeqMapper;

    @Override
    public int insert(DocumentIdSeq entity) {
        return documentIdSeqMapper.insert(entity);
    }
}
