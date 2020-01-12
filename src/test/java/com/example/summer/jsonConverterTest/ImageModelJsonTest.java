package com.example.summer.jsonConverterTest;

import com.example.summer.domain.ImagePO;
import com.example.summer.model.ImageModel;

import com.example.summer.stub.ImagePOStub;
import com.fasterxml.jackson.annotation.JsonInclude;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageModelJsonTest {
    @Test
    public void contextLoads() {
        ImagePO imagePO = new ImagePOStub().imagePOStub();
        ImageModel imageModel = new DozerBeanMapper().map(imagePO,ImageModel.class);
        Assert.assertEquals(JSONObject.fromObject(imagePO).toString(),
                JSONObject.fromObject(imageModel).toString());
        System.out.println(JSONObject.fromObject(imageModel).toString());
        System.out.println(JSONObject.fromObject(imagePO).toString());
    }
}
