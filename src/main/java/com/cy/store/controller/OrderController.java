package com.cy.store.controller;

import com.cy.store.entity.Order;
import com.cy.store.service.IOrderService;
import com.cy.store.util.JsonResult;
import com.cy.store.vo.OrderVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("orders")
@RestController
public class OrderController extends BaseController{
    @Autowired
    private IOrderService orderService;

    @PostMapping("/createOrder")
    public JsonResult<Order> createOrder(Integer aid,Long totalPrice,HttpSession session){
        //从session中取出用户名和uid
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);

        //调用业务层方法执行插入操作
        Order order = orderService.insertOrder(aid, totalPrice, uid, username);

        return new JsonResult<>(OK,order);
    }

    @PostMapping("/createOrderItem")
    public JsonResult<Void> createOrderItem(Integer oid,Integer cid,Integer pid,Integer num,HttpSession session){
        //从session中取出用户名
        String username = getUsernameFromSession(session);

        if (pid == null){ //当pid等于null代表是从购物车进入界面发来的请求
            //调用业务层方法执行插入操作
            orderService.insertOrderItem(oid,cid,num,username);
        }else {//当pid不等于null代表是从商品详情进入界面发来的请求
            //调用业务层方法执行插入操作
            orderService.insertOrderItemFromProductHtml(oid,pid,num,username);
        }

        return new JsonResult<>(OK);
    }

    @GetMapping("/queryOrder")
    public JsonResult<Order> queryOrderByOid(Integer oid){
        Order order = orderService.queryOrderByOid(oid);
        return new JsonResult<>(OK,order);
    }

    @PostMapping("/updateStatus")
    public JsonResult<Void> updateStatusByOid(Integer oid,HttpSession session,Integer status){
        //获取uid
        Integer uid = getuidFromSession(session);
        //修改订单状态
        orderService.updateOrderStatusByOid(oid,uid,status);

        return new JsonResult<>(OK);
    }

    @GetMapping("/queryOrderVo")
    public JsonResult<List<OrderVO>>  queryOrderVo(Integer oid){
        List<OrderVO> orderVos = orderService.queryOrderVoByOid(oid);

        return new JsonResult<>(OK,orderVos);
    }

    @GetMapping("/uidOrderVo")
    public JsonResult<List<OrderVO>>  queryOrderVoByUid(HttpSession session,Integer status){
        Integer uid = getuidFromSession(session);
        List<OrderVO> orderVos = orderService.queryOrderVoByUid(uid,status);

        return new JsonResult<>(OK,orderVos);
    }
}
