package com.cy.store.service.impl;

import com.cy.store.entity.*;
import com.cy.store.mapper.OrderMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ICartService;
import com.cy.store.service.IOrderService;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.OrderNotExistException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import com.cy.store.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IProductService productService;


    @Override
    public Order insertOrder(Integer aid, Long totalPrice, Integer uid, String username) {
        Address address = addressService.queryAddressByAid(aid);
        Order order = new Order();
        order.setUid(uid);
        order.setAid(aid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setTotalPrice(totalPrice);
        order.setStatus(0); //表示未支付
        Date createdTime = new Date();
        order.setOrderTime(createdTime);
        order.setPayTime(null);
        order.setCreatedUser(username);
        order.setModifiedUser(username);
        order.setCreatedTime(createdTime);
        order.setModifiedTime(createdTime);

        int res = orderMapper.insertOrder(order);
        if(res == 0){
            throw new InsertException("服务器出现错误，创建订单失败");
        }
        return orderMapper.queryOrderByOid(order.getOid());
    }

    @Override
    public int insertOrderItem(Integer oid, Integer cid, Integer num, String username) {
        Cart cart = cartService.queryCartVoByCid(cid);
        Integer pid = cart.getPid();
        Product product = productService.findById(pid);
        OrderItem orderItem = new OrderItem();

        //补全orderItem对象的空白字段
        orderItem.setOid(oid);
        orderItem.setPid(pid);
        orderItem.setTitle(product.getTitle());
        orderItem.setImage(product.getImage());
        orderItem.setPrice(product.getPrice());
        orderItem.setNum(num);
        Date createdTime = new Date();
        orderItem.setCreatedUser(username);
        orderItem.setCreatedTime(createdTime);
        orderItem.setModifiedUser(username);
        orderItem.setModifiedTime(createdTime);

        //调用持久层进行插入
        int result = orderMapper.insertOrderItem(orderItem);
        if (result == 0){
            throw new InsertException("服务器出现错误，创建订单失败");
        }
        return result;
    }

    @Override
    public int insertOrderItemFromProductHtml(Integer oid, Integer pid, Integer num, String username) {
        Product product = productService.findById(pid);

        OrderItem orderItem = new OrderItem();

        orderItem.setOid(oid);
        orderItem.setPid(pid);
        orderItem.setTitle(product.getTitle());
        orderItem.setImage(product.getImage());
        orderItem.setPrice(product.getPrice());
        orderItem.setNum(num);
        Date createdTime = new Date();
        orderItem.setCreatedUser(username);
        orderItem.setCreatedTime(createdTime);
        orderItem.setModifiedUser(username);
        orderItem.setModifiedTime(createdTime);
        int result = orderMapper.insertOrderItem(orderItem);
        if (result == 0){
            throw new InsertException("服务器出现错误，创建订单失败");
        }
        return result;
    }

    @Override
    public Order queryOrderByOid(Integer oid) {
        Order order = orderMapper.queryOrderByOid(oid);
        if(order == null){
            throw new OrderNotExistException("订单不存在！！！");
        }
        return order;
    }

    @Override
    public int updateOrderStatusByOid(Integer oid, Integer uid, Integer status) {
        //先查询一下订单信息
        Order order = orderMapper.queryOrderByOid(oid);
        if(order == null){
            throw new OrderNotExistException("无订单信息！！！");
        }

        int result = 0;
        //status == 0代表刚刚创建
        if (order.getStatus() == 0){
            //修改支付时间
            Date payTime = new Date();
            result = orderMapper.updateStatusByOidInt(oid, status,payTime);

            //根据oid查找具体的OrderItem信息
            List<OrderItem> orderItems = orderMapper.queryOrderItemByOid(oid);
            for (OrderItem o: orderItems) {
                //从OrderItem中取得pid
                Integer pid = o.getPid();
                //根据pid和uid删除购物车中的商品
                cartService.deleteCartByUidAndPid(uid, pid);
            }
        }else {
            //除了status == 0的状况其他的都可以直接修改其状态
            //修改订单状态
            result = orderMapper.updateStatusByOidInt(oid,status,order.getPayTime());
        }

        if (result == 0){
            throw new UpdateException("服务器异常，修改订单状态失败");
        }

        return result;
    }

    @Override
    public List<OrderItem> queryOrderItemByOid(Integer oid) {
        List<OrderItem> orderItems = orderMapper.queryOrderItemByOid(oid);

        if (orderItems.isEmpty()){
            throw new OrderNotExistException("订单不存在！！！");
        }

        return orderItems;
    }

    @Override
    public List<OrderVO> queryOrderVoByOid(Integer oid) {
        List<OrderVO> orderVos = orderMapper.queryOrderVOByOid(oid);
        for (OrderVO vo: orderVos) {
            //根据每个订单的oid查询地址信息
            Address address = addressService.queryAddressByAid(vo.getAid());
            //补全OrderVo值对象中的空白字段
            vo.setZip(address.getZip());
            vo.setPhone(address.getPhone());
            vo.setProvinceName(address.getProvinceName());
            vo.setCityName(address.getCityName());
            vo.setAreaName(address.getAreaName());
            vo.setAddress(address.getAddress());
        }

        return orderVos;
    }

    @Override
    public List<OrderVO> queryOrderVoByUid(Integer uid, Integer status) {
        List<OrderVO> orderVos = orderMapper.queryOrderVOByUid(uid,status);
        if (orderVos.isEmpty()){
            throw new OrderNotExistException("查询订单为空");
        }
        return orderVos;
    }
}
