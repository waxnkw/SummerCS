package com.example.summer.web;

import com.example.summer.model.UserModel;
import com.example.summer.service.LoginService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * @program: summerCS_Phase_I
 * @description: LogCotroller
 * @author: 161250193
 * @create: 2018/3/17
 */

@RestController
public class LogController {
    @Autowired
    private LoginService loginService;
    /**
     * 用户登录
     * @param logStr 用户名
     * @param password 用户密码
     * @return 该用户能否登陆
     * */
    @RequestMapping("/login")
    public boolean login(@RequestParam("logStr") String logStr, @RequestParam("password") String password){
        //TODO debug
        return loginService.login(logStr, password);
    }

    /**
     * 用户注册
     * @param user 用户的注册信息
     * @return 是否注册成功
     * */
    @RequestMapping("/register")
    public boolean register(@RequestBody UserModel user){
        System.out.println(JSONObject.fromObject(user).toString());
        return loginService.register(user);
    }
}
