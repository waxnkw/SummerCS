package com.example.summer.model;

import com.example.summer.dao.UserDao;
import com.example.summer.daoImpl.UserDaoImpl;
import com.example.summer.domain.UserPO;
import com.example.summer.utility.dozerSingletonUtility.DozerMapSingleton;
import com.example.summer.utility.json.deserialzer.EmailModelDeserializer;
import com.example.summer.utility.json.serializer.EmailModelSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/*
 * @program: summerCS_Phase_I
 * @description: User的model模型
 * @author: 161250193
 * @create: 2018/3/17
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserModel implements Model{
    @JsonIgnore
    private String id;

    private int totalCredit;

    private String username;

    private String password;

    @JsonSerialize(using = EmailModelSerializer.class)
    @JsonDeserialize(using = EmailModelDeserializer.class)
    private EmailModel email;

    /**
     * 验证用户是否合法
     * @param logStr 登陆的用户名或者密码
     * @param password 登陆的密码
     * */
    public static boolean isValidUser(String logStr, String password){
        UserDao userDao = new UserDaoImpl();
        return userDao.isValidUser(logStr, password);
    }


    //@Autowired
    @JsonIgnore
    private static UserDao userDao = new UserDaoImpl();
    /**
     *
     * */
    public boolean register(){
        UserPO po = DozerMapSingleton.getInstance().map(this, UserPO.class);
        //System.out.println(JSONObject.fromObject(po).toString());
        return userDao.register(po);
    }

    public static ArrayList<UserModel> listAllUsers(){
        ArrayList<UserPO> pos = userDao.listAllUsers();
        ArrayList<UserModel> models = new ArrayList<>();
        pos.forEach(e->models.add(DozerMapSingleton.getInstance().map(e, UserModel.class)));
        return models;
    }
}
