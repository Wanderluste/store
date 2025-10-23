package com.cy.store.service;

import com.cy.store.entity.User;

/*用户模块业务接口*/
public interface IUserService {
    /**
     * 用户注册方法
     * @param user 用户的数据对象
     */
    void reg(User user);

    /**
     *用户登录功能
     * @param username 用户名
     * @param password 密码
     * @return 返回匹配的用户数据
     */
    User login(String username, String password);
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    /**
     * 根据用户uid查询数据
     * @param uid
     * @return
     */
    User getByUid(Integer uid);

    /**
     * 修改个人信息
     * @param uid 用户uid
     * @param username 用户名
     * @param user 用户数据
     */
    void changeInfo(Integer uid, String username, User user);

    /**
     * 修改用户头像
     * @param uid id
     * @param avatar 用户头像
     * @param username 用户名
     */
    void changeAvatar(Integer uid, String avatar, String username);
}
