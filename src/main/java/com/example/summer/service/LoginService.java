package com.example.summer.service;

import com.example.summer.model.UserModel;

public interface LoginService {

    /**
     * 注册：用户名和邮箱都不能重复
     * @param userModel 用户信息
     * @return 是否注册成功
     * */
    public boolean register(UserModel userModel) ;

    /**
     * 登录
     * @param logStr 用户名或者密码
     * @param password 密码
     * @return 是否登录成功
     * */
    public boolean login(String logStr, String password);
}
