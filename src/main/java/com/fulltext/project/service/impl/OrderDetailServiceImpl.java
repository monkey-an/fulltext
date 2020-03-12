package com.fulltext.project.service.impl;

import com.fulltext.project.dao.OrderDetailMapper;
import com.fulltext.project.entity.OrderDetail;
import com.fulltext.project.service.OrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {
    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetail selectOrderDetailById(Long id) {
        return null;
    }

    @Override
    public List<OrderDetail> selectOrderDetailListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(OrderDetail entity) {
        return 0;
    }

    @Override
    public int update(OrderDetail entity) {
        return 0;
    }
}
