package com.fulltext.project.service;
import com.fulltext.project.entity.OrderDetail;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface OrderDetailService {
    OrderDetail selectOrderDetailById(Long id);
    List<OrderDetail> selectOrderDetailListByIdList(List<Long> idList);
    int insert(OrderDetail entity);
    int update(OrderDetail entity);
}