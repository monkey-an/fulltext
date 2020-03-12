package com.fulltext.project.service;
import com.fulltext.project.entity.Payment;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface PaymentService {
    Payment selectPaymentById(Long id);
    List<Payment> selectPaymentListByIdList(List<Long> idList);
    int insert(Payment entity);
    int update(Payment entity);
}