package com.fulltext.project.service.impl;

import com.fulltext.project.dao.OrderMapper;
import com.fulltext.project.entity.Order;
import com.fulltext.project.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public Order selectOrderById(Long id) {
        return null;
    }

    @Override
    public List<Order> selectOrderListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(Order entity) {
        return 0;
    }

    @Override
    public int update(Order entity) {
        return 0;
    }
}
