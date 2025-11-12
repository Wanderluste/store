package com.cy.store.controller;

import com.cy.store.entity.Cart;
import com.cy.store.service.ICartService;
import com.cy.store.util.JsonResult;
import com.cy.store.vo.CartVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("carts")
@RestController
public class CartController extends BaseController{
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid,
                                      Integer amount,
                                      HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        cartService.addToCart(uid, pid, amount, username);
        return new JsonResult<>(OK);
    }
    @RequestMapping({"", "/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        List<CartVO> list = cartService.getVOByUid(getuidFromSession(session));
        return new JsonResult<>(OK, list);
    }
    @RequestMapping("/{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid,
                                      HttpSession session) {
        Integer data = cartService.addNum(cid, getuidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK, data);
    }
    @RequestMapping("list")
    public JsonResult<List<CartVO>> getVOByCid(Integer[] cids, HttpSession session) {
        List<CartVO> data = cartService.getVOByCid(getuidFromSession(session), cids);
        return new JsonResult<>(OK, data);
    }
    @PostMapping("/updateCart")
    public JsonResult<Void> updateCartByCid(Integer num, Integer cid, HttpSession session) {
        String username = getUsernameFromSession(session);
        cartService.updateCartNumByCid(num, cid, username, new Date());
        return new JsonResult<>(OK);
    }

    @PostMapping("/deleteCart")
    public JsonResult<Void> deleteCartByCid(Integer[] cids) {
        for (Integer cid : cids) {
            cartService.deleteCartByCid(cid);
        }
        return new JsonResult<>(OK);
    }
    @GetMapping("/showCarts")
    public JsonResult<List<CartVO>> showCarts(HttpSession session){
        Integer uid = getuidFromSession(session);
        List<CartVO> carts = cartService.getVOByUid(uid);

        return new JsonResult<>(OK,carts);
    }

}
