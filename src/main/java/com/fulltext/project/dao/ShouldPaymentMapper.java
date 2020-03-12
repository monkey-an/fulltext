package com.fulltext.project.dao;

import com.fulltext.project.entity.ShouldPayment;
import java.util.List;

public interface ShouldPaymentMapper {
    
    int deleteByPrimaryKey(Long id);

    
    int insert(ShouldPayment record);

    
    ShouldPayment selectByPrimaryKey(Long id);

    
    List<ShouldPayment> selectAll();

    
    int updateByPrimaryKey(ShouldPayment record);
}