package com.fulltext.project.service;
import com.fulltext.project.entity.ShouldPayment;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface ShouldPaymentService {
    ShouldPayment selectShouldPaymentById(Long id);
    List<ShouldPayment> selectShouldPaymentListByIdList(List<Long> idList);
    int insert(ShouldPayment entity);
    int update(ShouldPayment entity);
}