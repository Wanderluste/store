package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username = user.getUsername();
        User result = userMapper.findByUsername(username);
        if(result != null) {
            //抛出异常
            throw new UsernameDuplicatedException("用户名被占用");
        }
        //还需要加密处理
        //串+password+串->md5加密，连续加载三次
        //salt + password + salt salt is a random number.
        String oldPassword = user.getPassword();
        //获取salt
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        //salt+password
        String md5Password = getMD5Password(oldPassword, salt);
        //将加密后的密码重新补全
        user.setPassword(md5Password);
        //补全数据
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("产生未知异常");
        }
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }
        String oldPassword = result.getPassword();
        String salt = result.getSalt();
        String newMd5Password = getMD5Password(password, salt);
        if (!newMd5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("用户密码错误");
        }
        //判断是否被删除
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        //减少数据量传输的体量
        //提升性能
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(username);
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        String oldMD5Password = getMD5Password(oldPassword, result.getSalt());
        if (!result.getPassword().equals(oldMD5Password)) {
            throw new PasswordNotMatchException("密码错误");
        }
        String newMD5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMD5Password, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据产生未知异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        //重新封装，轻量化数据
        User user = new User();
        user.setUsername(result.getUsername());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        //漏传了phone的值！！！！！！
        user.setPhone(result.getPhone());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        //轻量化数据，只传需要修改的或判断的
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知异常");
        }
    }


    /*md5加密方法*/
    private String getMD5Password(String password, String salt) {
        //md5
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toLowerCase();
        }
        return password;
    }
}
