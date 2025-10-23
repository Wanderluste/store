package com.cy.store.controller;

import com.cy.store.entity.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("addresses")
@RestController
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;
    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid, username, address);
        return new JsonResult<>(OK);
    }
    @RequestMapping({"", "/"})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid = getuidFromSession(session);
        List<Address> list = addressService.getByUid(uid);
        return new JsonResult<>(OK, list);
    }
    //restful
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid")Integer aid, HttpSession session){
        addressService.setDefault(aid, getuidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid,
                                   HttpSession session){
        addressService.delete(aid,
                              getuidFromSession(session),
                              getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
    @GetMapping("/queryOneAddress")
    public JsonResult<Address> queryOneAddress(Integer aid){
        Address address = addressService.queryAddressByAid(aid);
        address.setCreatedUser(null);
        address.setModifiedUser(null);
        address.setModifiedUser(null);
        address.setCreatedTime(null);
        address.setIsDefault(0);
        return new JsonResult<>(OK, address);
    }
    @PostMapping("/updateAddress")
    public JsonResult<Void> updateAddress(Address address, HttpSession session){
        String modifiedUser = getUsernameFromSession(session);
        int res = addressService.updateOneAddress(address, modifiedUser);
        if (res == 0) {
            throw new InsertException("修改地址时，服务器或数据库异常");
        }
        return new JsonResult<>(OK);
    }
}
