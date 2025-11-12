package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.vo.OrderVO;

import java.util.Date;
import java.util.List;

public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order 订单对象
     * @return 影响行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单项数据
     * @param orderItem 订单项数据
     * @return 影响行数
     */
    Integer insertOrderItem(OrderItem orderItem);

    Order queryOrderByOid(Integer oid);

    Integer updateStatusByOidInt(Integer oid, Integer status, Date payTime);

    List<OrderItem> queryOrderItemByOid(Integer oid);

    List<OrderVO> queryOrderVOByOid(Integer oid);

    List<OrderVO> queryOrderVOByUid(Integer uid, Integer status);
}
