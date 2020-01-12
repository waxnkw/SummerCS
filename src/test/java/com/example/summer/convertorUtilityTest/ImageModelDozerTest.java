package com.example.summer.convertorUtilityTest;

import com.example.summer.domain.ImagePO;
import com.example.summer.model.ImageModel;
import com.example.summer.stub.ImagePOStub;
import com.example.summer.utility.dozerSingletonUtility.DozerMapSingleton;
import net.sf.json.JSONObject;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.junit.Test;

public class ImageModelDozerTest {
    @Test
    public void test(){
        ImagePO po = new ImagePOStub().imagePOStub();
        System.out.println(JSONObject.fromObject(po).toString());
        ImageModel model = DozerMapSingleton.getInstance().map(po, ImageModel.class);
        System.out.println(JSONObject.fromObject(model).toString());
    }
}
