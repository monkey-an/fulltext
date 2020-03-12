package com.fulltext.project.service.impl;

import com.fulltext.project.dao.OrderIdSeqMapper;
import com.fulltext.project.entity.OrderIdSeq;
import com.fulltext.project.service.OrderIdSeqService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class OrderIdSeqServiceImpl implements OrderIdSeqService {
    @Resource
    private OrderIdSeqMapper orderIdSeqMapper;

    @Override
    public OrderIdSeq selectOrderIdSeqById(Long id) {
        return null;
    }

    @Override
    public List<OrderIdSeq> selectOrderIdSeqListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(OrderIdSeq entity) {
        return orderIdSeqMapper.insert(entity);
    }
}
