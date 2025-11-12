package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.Favorites;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**收货地址持久层接口*/
@Mapper
public interface FavoritesMapper {
    //添加收藏
    Integer addFavorites(Favorites favorites);
    //查询
    List<Favorites> getFavorites(Integer uid, Integer status);
    //更新操作后的收藏表单
    Integer updateFavorites(Integer status, Integer fid, Integer uid);
}
