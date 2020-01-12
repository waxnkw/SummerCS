package com.example.summer.modelTest;

import com.example.summer.dao.ImageDao;
import com.example.summer.daoImpl.ImageDaoImpl;
import com.example.summer.domain.EmailPO;
import com.example.summer.domain.ImagePO;
import com.example.summer.domain.UserPO;
import com.example.summer.model.UserModel;
import net.sf.json.JSONObject;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.junit.Test;

import java.util.ArrayList;

public class SingleUserImageModelTest {
    @Test
    public void testUpdate(){
        //SingleUserImageModel singleUserImageModel = new SingleUserImageModel();
        //singleUserImageModel.setImageId("2018-03-24_22-12-43--0");
//        ImageDao dao = new ImageDaoImpl();
//        ImagePO imagePO = dao.queryImageByImageId("2018-03-27_00-04-42--0");
//        System.out.println("SingleUserImageModelTest.testUpdate:"+ JSONObject.fromObject(imagePO).toString());
        UserPO userPO = new UserPO();
        userPO.setUsername("zhangao");
        userPO.setEmail(new EmailPO("sds"));

        UserModel userModel = DozerBeanMapperSingletonWrapper.getInstance().map(userPO, UserModel.class);
        System.out.println(JSONObject.fromObject(userPO).toString());
        System.out.println(JSONObject.fromObject(userModel).toString());
    }
}
