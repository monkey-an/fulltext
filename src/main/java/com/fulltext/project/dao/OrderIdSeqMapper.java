package com.fulltext.project.dao;

import com.fulltext.project.entity.OrderIdSeq;

import java.util.List;

public interface OrderIdSeqMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(OrderIdSeq record);


    List<OrderIdSeq> selectAll();
}