package com.fulltext.project.service.impl;

import com.fulltext.project.dao.PaymentMapper;
import com.fulltext.project.entity.Payment;
import com.fulltext.project.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public Payment selectPaymentById(Long id) {
        return null;
    }

    @Override
    public List<Payment> selectPaymentListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(Payment entity) {
        return 0;
    }

    @Override
    public int update(Payment entity) {
        return 0;
    }
}
