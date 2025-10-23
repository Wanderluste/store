package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**收货地址持久层接口*/
@Mapper
public interface AddressMapper {
    /**
     * 插入用户的收货地址数据
     * @param address 收货地址数据
     * @return 受影响的行数
     */

    Integer insert(Address address);

    /**
     * 根据用户的uid统计收货地址数量
     * @param uid 用户id
     * @return 当前用户的收货地址总数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户的id查询收货地址
     * @param uid 用户id
     * @return 收获地址数据
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据aid查寻收货地址是否存在
     * @param aid 收货地址id
     * @return 收货地址数据，没有则为null
     */
    Address findByAid(Integer aid);

    /**
     * 根据 uid 值来修改用户收货地址是否为默认
     * @param uid 用户id
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * combination
     * @param aid date's aid
     * @param modifiedUser changed
     * @param modifiedTime changed
     * @return res
     */
    Integer updateDefaultByAid(@Param("aid") Integer aid,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);
    /**
     *根据 uid 删除地址
     * @param aid 地址
     * @return 受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 查询用户最新的修改的地址
     * @param uid 用户id
     * @return 收获地址数据
     */
    Address findLastModified(Integer uid);

    /**
     * 编辑收货地址信息
     * @param address 地址
     * @return 行数
     */
    Integer updateAddress(Address address);
}
