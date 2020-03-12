package com.fulltext.project.dao;

import com.fulltext.project.entity.OrderDetail;
import java.util.List;

public interface OrderDetailMapper {

    int deleteByPrimaryKey(Long id);


    int insert(OrderDetail record);


    OrderDetail selectByPrimaryKey(Long id);


    List<OrderDetail> selectAll();


    int updateByPrimaryKey(OrderDetail record);
}