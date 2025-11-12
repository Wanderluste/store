package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.vo.OrderVO;

import java.util.List;

public interface IOrderService {
    //处理添加order订单数据的抽象方法
    Order insertOrder(Integer aid, Long totalPrice, Integer uid, String username);

    //处理添加orderItem数据的抽象方法
    int insertOrderItem(Integer oid, Integer cid, Integer num, String username);

    //处理从product页面直接购买添加orderItem数据的抽象方法
    int insertOrderItemFromProductHtml(Integer oid,Integer pid, Integer num, String username);

    //根据oid查询order信息的抽象方法
    Order queryOrderByOid(Integer oid);

    //根据oid修改oid的订单状态的抽象方法
    int updateOrderStatusByOid(Integer oid,Integer uid,Integer status);

    //根据oid能从order_item表中找到对应的OrderItem信息的抽象方法
    List<OrderItem> queryOrderItemByOid(Integer oid);

    //根据订单oid查询订单的抽象方法
    List<OrderVO> queryOrderVoByOid(Integer oid);

    //根据用户uid查询订单的抽象方法
    List<OrderVO> queryOrderVoByUid(Integer uid,Integer status);

}
