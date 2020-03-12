package com.fulltext.project.service;
import com.fulltext.project.entity.Order;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface OrderService {
    Order selectOrderById(Long id);
    List<Order> selectOrderListByIdList(List<Long> idList);
    int insert(Order entity);
    int update(Order entity);
}