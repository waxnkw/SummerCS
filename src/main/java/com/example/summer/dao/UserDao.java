package com.example.summer.dao;

import com.example.summer.domain.UserPO;

import java.util.ArrayList;

public interface UserDao {
    /**
     * 判断登录是否正确
     * @param logStr  可能为email，也可能为username
     * @param password 用户密码
     * @return 是否登陆成功
     * */
    public boolean isValidUser(String logStr, String password);

    /**
     * 是否存在该用户
     * @param username  用户名
     * */
    public boolean isExistedUserByUserName(String username);

    /**
     * 是否存在该用户
     * @param emailAddress  邮箱
     * */
    public boolean isExistedUserByEmail(String emailAddress);

    /**
     * 注册该用户
     * 储存用户信息
     * @param po 注册用户信息
     * @return 新标记的图片
     * */
    public  boolean register(UserPO po);

    /**
     * 根据用户名查询用户信息
     * @param username  用户名
     * @return 用户信息
     * */
    public UserPO queryUserByUsername(String username);

    /**
     * 获得所有用户
     * @return 所有用户
     * */
    public ArrayList<UserPO> listAllUsers();

}
