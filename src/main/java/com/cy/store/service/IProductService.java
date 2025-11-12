package com.cy.store.service;

import com.cy.store.entity.Product;
import com.github.pagehelper.PageInfo;

import java.util.List;

/** 商品业务层接口  */
public interface IProductService {
    List<Product> findHotList();
    List<Product> findNewList();
    Product findById(Integer id);
    PageInfo<Product> queryProductByTitle(Integer pageNum, Integer pageSize, String title);
}
