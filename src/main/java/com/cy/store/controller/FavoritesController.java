package com.cy.store.controller;

import com.cy.store.entity.Address;
import com.cy.store.entity.Favorites;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IFavoritesService;
import com.cy.store.util.JsonResult;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/favorites")
@RestController
public class FavoritesController extends BaseController{
    @Autowired
    private IFavoritesService favoritesService;

    @GetMapping("/getFavorites")
    public JsonResult<PageInfo<Favorites>> getFavorites(HttpSession session, Integer pageNum, Integer pageSize, Integer status){
        Integer uid = getuidFromSession(session);
        PageInfo<Favorites> favorites = favoritesService.queryFavorites(uid, pageNum, pageSize, status);
        return new JsonResult<>(OK, favorites);
    }
    @PostMapping("/addFavorites")
    public JsonResult<Integer> addFavorites(HttpSession session, Integer pid){
        Integer uid = getuidFromSession(session);
        Integer fid = favoritesService.addFavorites(uid, pid);
        return new JsonResult<>(OK, fid);
    }
    @PostMapping("/updateStatus")
    public JsonResult<Void> updateStatus(HttpSession session, Integer status, Integer fid){
        Integer uid = getuidFromSession(session);
        favoritesService.updateFavoritesStatus(status, fid, uid);
        return new JsonResult<>(OK);
    }
}
