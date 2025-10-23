package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.countByUid(uid);
        if (count > maxCount) {
            throw new AddressCountLimitException("用户收货地址超出上限");
        }
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        address.setUid(uid);
        //1为默认，0为不默认
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        //补全4项日志
        address.setModifiedUser(username);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("插入用户地址产生未知异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        //数据轻量化
        for (Address address : list) {
            //address.setAid(null);
            //address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setTel(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setIsDefault(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address res = addressMapper.findByAid(aid);
        if (res == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        if (!res.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        Integer rows1 = addressMapper.updateNonDefault(uid);
        if (rows1 < 1) {
            throw new UpdateException("更新数据产生未知错误");
        }
        Integer rows2 = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (rows2 != 1) {
            throw new UpdateException("更新数据产生未知错误");
        }
    }



    /**
     * 删除收货地址
     * @param aid 收货地址编号
     * @param uid 用户编号
     * @param username 用户名
     */
    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("地址信息不存在！");
        }
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问！");
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if (rows != 1) {
            throw new DeleteException("删除数据产生未知异常");
        }
        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            return;
        }
        if (result.getIsDefault() == 0) {
            return;
        }
        Address address = addressMapper.findLastModified(uid);
        rows = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知异常！");
        }

    }

    @Override
    public Address queryAddressByAid(Integer aid) {
        return addressMapper.findByAid(aid);
    }

    @Override
    public void updateOneAddress(Address address, String modifiedUser) {
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());

        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);
        //address.setUid(uid);

        address.setModifiedUser(modifiedUser);
        address.setModifiedTime(new Date());
        int res = addressMapper.updateAddress(address);
        System.out.println("Rows affected: " + res);
        if (res == 0) {
            throw new UpdateException("更新失败！");
        }
    }
}
