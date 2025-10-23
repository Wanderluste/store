package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/*用户模块的持久层接口 */
//查询语句->用户名是否存在，维持唯一性，在用户层返回
//插入数据->用户注册
@Mapper
public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 受影响的行数，(增删改，都受影响的行数作为返回值
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户数据
     * @param username 用户名
     * @return 查询到则返回这个用户的数据，没有找到则返回null值
     */
    User findByUsername(String username);

    /**
     * 根据Uid修改用户密码
     * @param uid 用户id
     * @param password 密码
     * @param modifiedUser 执行者
     * @param modifiedTime 修改时间
     * @return 返回值为受影响的行数
     */
    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    /**
     * 根据用户id查询用户数据
     * @param uid 用户id
     * @return 返回对象或null
     */
    User findByUid (Integer uid);

    /**
     * 参数为user的方法
     * @param user 用户数据
     * @return 返回值为受影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * 根据uid修改头像
     * @Param("SQL映射文件占位符的变量名")
     * 和映射接口变量名不一致时，强制转换
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
}
