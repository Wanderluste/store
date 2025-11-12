package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.Favorites;
import com.github.pagehelper.PageInfo;

import java.util.List;

/** 收货地址业务层接口  */
public interface IFavoritesService {
    int addFavorites(Integer uid,Integer pid);


    //查询收藏商品的抽象方法
    PageInfo<Favorites> queryFavorites(Integer uid, Integer pageNum, Integer pageSize, Integer status);

    //根据收藏商品fid和用户uid取消对应商品收藏的抽象方法
    int updateFavoritesStatus(Integer status,Integer fid,Integer uid);
}
