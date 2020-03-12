package com.fulltext.project.dao;

import com.fulltext.project.entity.Payment;
import java.util.List;

public interface PaymentMapper {

    int deleteByPrimaryKey(Long id);


    int insert(Payment record);


    Payment selectByPrimaryKey(Long id);


    List<Payment> selectAll();


    int updateByPrimaryKey(Payment record);
}