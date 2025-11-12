package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.Favorites;
import com.cy.store.entity.Product;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.FavoritesMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.IFavoritesService;
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
public class FavoritesServiceImpl implements IFavoritesService {
    @Autowired
    private FavoritesMapper favoritesMapper;
    @Autowired
    private IProductService productService;


    @Override
    public int addFavorites(Integer uid, Integer pid) {
        Favorites favorites = new Favorites();
        Product product = productService.findById(pid);

        favorites.setUid(uid);
        favorites.setPid(pid);
        favorites.setImage(product.getImage());
        favorites.setPrice(product.getPrice());
        favorites.setTitle(product.getTitle());
        favorites.setSellPoint(product.getSellPoint());
        favorites.setStatus(1);
        int res = favoritesMapper.addFavorites(favorites);
        if (res == 0) {
            throw new InsertException("插入数据异常");
        }
        return favorites.getFid();
    }

    @Override
    public PageInfo<Favorites> queryFavorites(Integer uid, Integer pageNum, Integer pageSize, Integer status) {
        PageHelper.startPage(pageNum, pageSize);
        List<Favorites> favorites = favoritesMapper.getFavorites(uid, status);
        PageInfo<Favorites> pageInfo = new PageInfo<>(favorites);
        return pageInfo;
    }

    @Override
    public int updateFavoritesStatus(Integer status, Integer fid, Integer uid) {
        int res = favoritesMapper.updateFavorites(status, fid, uid);
        if (res == 0) {
            throw new UpdateException("更新数据异常！");
        }
        return res;
    }
}
