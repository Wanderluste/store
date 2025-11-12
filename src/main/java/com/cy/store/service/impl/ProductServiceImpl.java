package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.Product;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            product.setPriority(null);
            product.setModifiedTime(null);
            product.setModifiedUser(null);
            product.setCreatedTime(null);
            product.setCreatedUser(null);
        }
        return list;
    }

    @Override
    public List<Product> findNewList() {
        return productMapper.findNewList();
    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("访问的商品数据不存在！");
        }
        product.setPriority(null);
        product.setModifiedTime(null);
        product.setModifiedUser(null);
        product.setCreatedTime(null);
        product.setCreatedUser(null);
        return product;
    }

    @Override
    public PageInfo<Product> queryProductByTitle(Integer pageNum, Integer pageSize, String title) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productMapper.queryProductByTitle(title);
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
