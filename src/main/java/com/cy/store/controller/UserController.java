package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//function: 异常全局处理
//@Controller
@RestController//Rest&Con
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    /**
     *1.接收数据的方式：请求参数的列表设置为pojo类型来接收前端的数据
     *SpringBoot会将前端的url地址中的参数名和pojo类的属性名进行比较
     *如果两个项目名一致，则降值注入到pojo对应的属性上
     * 2.请求类型为非pojo类型，则直接进行匹配
     */
    @RequestMapping("reg")
    //表示此格式的响应为JSON格式
    //@ResponseBody
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(OK);
    }
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);
        //向session对象中完成数据绑定
        //全局
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        //获取session中绑定的数据
        return new JsonResult<User>(OK, data);
    }
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }
    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK, data);
    }
    @RequestMapping("change_info")
    public  JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);
    }

    /**
     * Multipart为MVC提供的接口
     * @param session
     * @param file
     * @return
     */
    //限制头像的最大尺寸
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    //上传文件类型限制
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file) {
        //判断文件是否为空
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        //size
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件大小超出限制");
        }
        //type
        String type = file.getContentType();
        if (!AVATAR_TYPE.contains(type)) {
            throw new FileTypeException("文件类型不支持");
        }
        //上传文件
        String parent = "D:/Intelli2024/upload";
        //File对象指向路径判断是否存在
        File dir = new File(parent);
        if (!dir.exists()) {
            //不存在则创建新的文件夹
            dir.mkdirs();
        }
        //获取名称
        String originalFileName = file.getOriginalFilename();
        System.out.println(originalFileName);
        int index = originalFileName.lastIndexOf(".");
        //截取文件的后缀
        String suffix = originalFileName.substring(index);
        String fileName = UUID.randomUUID().toString().toUpperCase() + suffix;
        File dest = new File(dir, fileName);//空文件创建
        try {
            file.transferTo(dest);//将file文件写入dest中
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        //访问头像的相对路径
        String avatar = "/upload/" + fileName;
        userService.changeAvatar(uid, avatar, username);
        //返回用户头像路径给前端，便于展示
        return new JsonResult<>(OK, avatar);
    }
}
