package com.example.summer.convertorUtilityTest;

import com.example.summer.domain.UserInfoPO;
import com.example.summer.domain.UserPO;
import com.example.summer.model.EmailModel;
import com.example.summer.model.UserInfoModel;
import com.example.summer.model.UserModel;
import com.example.summer.utility.dozerSingletonUtility.DozerMapSingleton;
import net.sf.json.JSONObject;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.junit.Assert;
import org.junit.Test;

public class UserInfoDozerTest {
    @Test
    public  void test(){
        UserModel model = new UserModel();
        model.setUsername("username");
        model.setPassword("asd");
        model.setTotalCredit(51);
        model.setEmail(new EmailModel("132546@qq.com"));
        UserPO po = DozerMapSingleton.getInstance().map(model, UserPO.class);
        System.out.println(JSONObject.fromObject(po).toString());
    }
}
