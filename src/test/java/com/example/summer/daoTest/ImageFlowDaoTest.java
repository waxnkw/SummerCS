package com.example.summer.daoTest;

import com.example.summer.dao.ImageDao;
import com.example.summer.dao.ImageFlowDao;
import com.example.summer.daoImpl.ImageDaoImpl;
import com.example.summer.daoImpl.ImageFlowDaoImpl;
import com.example.summer.domain.ImagePO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ImageFlowDaoTest {
    ImageFlowDaoImpl imageFlowDao = new ImageFlowDaoImpl();
    ImageDao imageDao = new ImageDaoImpl();

    @Test
    public void testSaveProjectImages(){

    }

    @Test
    public void testInitImages(){
        String path = "D:\\idea\\java\\summerCS_Phase_II\\src\\test\\resources\\ProjectImagesTest\\proj123456\\train";
        imageFlowDao.initImages(path,"train","proj123456");
        ArrayList<ImagePO> imagePOS = imageDao.listImagesByProjectId("proj123456");
        for(ImagePO po: imagePOS){
            Assert.assertEquals("train",po.getSplit());
            Assert.assertEquals(1,po.getUpUserLimit());
            System.out.println(po.getFileName());
        }
    }
}
