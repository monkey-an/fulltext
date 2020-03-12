package com.fulltext.project.service;
import com.fulltext.project.entity.OrderIdSeq;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface OrderIdSeqService {
    OrderIdSeq selectOrderIdSeqById(Long id);
    List<OrderIdSeq> selectOrderIdSeqListByIdList(List<Long> idList);
    int insert(OrderIdSeq entity);
}