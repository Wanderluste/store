package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    /**
     * 插入购物车
     * @param cart 购物车数据
     * @return rows
     */
    Integer insert(Cart cart);

    /**
     *
     * 更新购物车某件商品的数量
     * @param cid 购物车id
     * @param num 购物数量
     * @param modifiedUser user
     * @param modifiedTime time
     * @return 受影响的行数
     */
    Integer updateNumByCid(Integer cid,
                           Integer num,
                           String modifiedUser,
                           Date modifiedTime);

    /**
     * 根据用户id和购物车id查询是否存在
     * @param uid 用户id
     * @param pid 购物车id
     * @return cart数据
     */
    Cart findByUidAndPid(Integer uid, Integer pid);

    List<CartVO> findVOByUid(Integer uid);

    Cart findByCid(Integer cid);
}
