package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 控制层基类
 */
@RestControllerAdvice
public class BaseController {
    /**操作成功*/
    public static final int OK = 200;
    /**统一处理错误，请求处理方法*/
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    //抛出异常
    //该方法充当请求处理方法，请求值直接返回前端
    public JsonResult<Void> handlerException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMassage("用户名已经被占用");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4001);
            result.setMassage("用户收货地址超出上限");
        } else if (e instanceof UserNotFoundException) {
            result.setState(4002);
            result.setMassage("用户数据不存在的异常");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4003);
            result.setMassage("用户名的密码错误的异常");
        }else if (e instanceof AddressNotFoundException) {
            result.setState(4004);
            result.setMassage("收货地址数据不存在");
        }else if (e instanceof AccessDeniedException) {
            result.setState(4005);
            result.setMassage("收货数据地址非法访问");
        }else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMassage("访问的商品数据不存在！");
        }else if (e instanceof CartNotFoundException) {
            result.setState(4007);
            result.setMassage("购物车数据不存在！");
        } else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMassage("更新数据产生异常");
        }else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMassage("上传文件为空");
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMassage("上传文件大小异常");
        } else if (e instanceof FileStateException) {
            result.setState(6002);
            result.setMassage("上传文件状态异常");
        }else if (e instanceof FileUploadIOException) {
            result.setState(6003);
            result.setMassage("上传文件IO错误");
        }else if (e instanceof FileTypeException) {
            result.setState(6004);
            result.setMassage("上传文件类型错误");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMassage("注册时产生未知错误");
        }else if (e instanceof DeleteException) {
            result.setState(5001);
            result.setMassage("删除时产生未知错误");
        }
        return result;
    }

    /**
     * 获取session当中的uid
     * @param session session对象
     * @return 当前用户登陆的uid值
     */
    //final方法不可继承/重写
    protected final Integer getuidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前用户的username
     * @param session session对象
     * @return 当前用户名
     */
    //从对话中获取信息
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}




















