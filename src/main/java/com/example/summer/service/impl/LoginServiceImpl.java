package com.example.summer.service.impl;
import com.example.summer.model.UserModel;
import com.example.summer.service.LoginService;

import org.springframework.stereotype.Service;

/*
 * @program: summerCS_Phase_I
 * @description:
 * @author: Mr.Wang
 * @create: 2018/3/21
 */
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * 注册：用户名和邮箱都不能重复
     *
     * @param userModel 用户信息
     * @return 是否注册成功
     */
    @Override
    public boolean register(UserModel userModel) {
        return userModel.register();
    }

    /**
     * 登录
     *
     * @param logStr   用户名或者密码
     * @param password 密码
     * @return 是否登录成功
     */
    @Override
    public boolean login(String logStr, String password) {
        return UserModel.isValidUser(logStr, password);
    }
}
