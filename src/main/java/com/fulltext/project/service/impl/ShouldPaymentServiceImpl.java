package com.fulltext.project.service.impl;

import com.fulltext.project.dao.ShouldPaymentMapper;
import com.fulltext.project.entity.ShouldPayment;
import com.fulltext.project.service.ShouldPaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class ShouldPaymentServiceImpl implements ShouldPaymentService {
    @Resource
    private ShouldPaymentMapper shouldPaymentMapper;

    @Override
    public ShouldPayment selectShouldPaymentById(Long id) {
        return null;
    }

    @Override
    public List<ShouldPayment> selectShouldPaymentListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(ShouldPayment entity) {
        return 0;
    }

    @Override
    public int update(ShouldPayment entity) {
        return 0;
    }
}
