package com.cy.store.service;

import com.cy.store.entity.Product;
import com.cy.store.vo.CartVO;

import java.util.List;

/** 购物车业务层接口  */
public interface ICartService {
    /**
     * 商品添加到购物车
     * @param uid 用户id
     * @param pid 购物车id
     * @param amount 数量
     * @param username 用户名
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    List<CartVO> getVOByUid(Integer uid);

    Integer addNum(Integer cid, Integer uid, String username);
}
