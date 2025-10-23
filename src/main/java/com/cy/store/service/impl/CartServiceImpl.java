package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.*;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    /** 购物车的业务层依赖于购物车的持久层和商品的持久层 */
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid,
                          Integer amount, String username) {
        Date date = new Date();
        //购物车是否已经存在
        Cart res = cartMapper.findByUidAndPid(uid, pid);
        if (res == null) {
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            Product product = productMapper.findById(pid);//?
            if (product == null) {
                throw new ProductNotFoundException("商品不存在！");
            }
            cart.setPrice(product.getPrice());
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedTime(date);
            cart.setModifiedUser(username);
            Integer rows = cartMapper.insert(cart);
            if (rows != 1) {
                throw new InsertException("插入数据异常！");
            }
        } else {
            //购物车相加
            Integer number = res.getNum() + amount;
            Integer rows = cartMapper.updateNumByCid(res.getCid(), number, username, date);
            if (rows != 1) {
                throw new UpdateException("更新数据产生异常！");
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
       Cart result = cartMapper.findByCid(cid);
       if (result == null) {
           throw new CartNotFoundException("购物车不存在！");
       }
       if (!result.getUid().equals(uid)) {
           throw new AccessDeniedException("用户数据非法访问！");
       }
       Integer num = result.getNum() + 1;
       Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
       if (rows != 1) {
           throw new UpdateException("更新失败！");
       }
       return num;
    }
}
