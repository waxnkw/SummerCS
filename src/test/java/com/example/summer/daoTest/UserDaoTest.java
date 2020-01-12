package com.example.summer.daoTest;


import com.example.summer.dao.UserDao;
import com.example.summer.domain.EmailPO;
import com.example.summer.domain.UserPO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    UserDao userDao;
    @Autowired
    UserPO userPO1;
    @Autowired
    UserPO userPO2;

    @Before
    public void initData(){
         userPO1.setUsername("beibei");
         EmailPO emailPO=new EmailPO();
         emailPO.setAddress("1292155474@qq.com");
         userPO1.setEmail(emailPO);
         userPO1.setPassword("12345");

        userPO2.setUsername("boogie");
        EmailPO emailPO2=new EmailPO();
        emailPO2.setAddress("boogieWang@qq.com");
        userPO2.setEmail(emailPO2);
        userPO2.setPassword("1996");

    }
    @Test
    public void testRegister(){
        userDao.register(userPO2);
    }
    @Test
    public void testQueryUserByUsername(){
        UserPO user=userDao.queryUserByUsername("beibei");
        Assert.assertEquals("12345",user.getPassword());
        Assert.assertEquals("1292155474@qq.com",user.getEmail().getAddress());
    }
    @Test
    public void testIsExistedUserByUserName(){
        Assert.assertEquals(true,userDao.isExistedUserByUserName("beibei"));
        Assert.assertEquals(false,userDao.isExistedUserByUserName("peipei"));
    }
    @Test
    public void testIsExistedUserByEmail(){
        Assert.assertEquals(true,userDao.isExistedUserByEmail("1292155474@qq.com"));
        Assert.assertEquals(false,userDao.isExistedUserByEmail("161250194@qq.com"));
    }
    @Test
    public void testIsValidUser(){
        Assert.assertEquals(true,userDao.isValidUser("beibei","12345"));
        Assert.assertEquals(true,userDao.isValidUser("1292155474@qq.com","12345"));
        Assert.assertEquals(false,userDao.isValidUser("161250194@qq.com","12345"));
        Assert.assertEquals(false,userDao.isValidUser("peipei","12345"));
        Assert.assertEquals(false,userDao.isValidUser("beibei","123456"));
        Assert.assertEquals(false,userDao.isValidUser("1292155474@qq.com","123456"));
        Assert.assertEquals(false,userDao.isValidUser("161250194@qq.com","123456"));
        Assert.assertEquals(false,userDao.isValidUser("peipei","123456"));

    }


}
