package com.example.summer.daoTest;

import com.example.summer.dao.ImageDao;
import com.example.summer.daoImpl.ImageDaoImpl;
import com.example.summer.domain.ImageFlowPO;
import com.example.summer.domain.ImagePO;
import com.example.summer.domain.MarkerInfoPO;
import com.example.summer.model.ImageListModel;
import com.example.summer.stub.ImagePOStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageDaoTest {

    ImagePO imagePO1=new ImagePO();

    ImagePO imagePO2=new ImagePO();

    ImageFlowPO imageFlowPO1=new ImageFlowPO();

    ImageFlowPO imageFlowPO2=new ImageFlowPO();

    ArrayList<ImagePO> imagePOs=new ArrayList<ImagePO>();
    ArrayList<ImageFlowPO> imageFlowPOS=new ArrayList<ImageFlowPO>();

    @Autowired
    ImageDao imageDao;

    @Before
    public void initData(){

         /*  MarkerInfoPO markerPO1=new MarkerInfoPO();
           markerPO1.setUsername("beibei");
           MarkerInfoPO markerPO2=new MarkerInfoPO();
           markerPO2.setUsername("boogie");
           ArrayList<MarkerInfoPO> markers=new ArrayList<MarkerInfoPO>();
           markers.add(markerPO1);
           markers.add(markerPO2);
           imagePO1.setMarkers(markers);
           imagePO2.setMarkers(markers);
*/

        ImagePOStub stub=new ImagePOStub();
        imagePO1=stub.imagePOStub();
        imagePO2=stub.imagePOStub();
           imagePOs.add(imagePO1);
           imagePOs.add(imagePO2);

           byte[] bytes1=null;
           byte[] bytes2=null;
        try {
            File imageFile1 = new File("boogie1.jpg");
            FileInputStream fis1 = new FileInputStream(imageFile1);
            bytes1=new byte[10240*10];//bytes1 save the bytes of the image1 no more than 100k
            fis1.read(bytes1);

            File imageFile2 = new File("boogie2.jpg");
            FileInputStream fis2 = new FileInputStream(imageFile2);
            bytes2=new byte[10240*10];//bytes2 save the bytes of the image2 no more than 100k
            fis2.read(bytes2);

        }catch(IOException e){
            e.printStackTrace();
        }

        imageFlowPO1.setImageBytes(bytes1);
        imageFlowPO2.setImageBytes(bytes2);
        imageFlowPOS.add(imageFlowPO1);
        imageFlowPOS.add(imageFlowPO2);


        ArrayList<ImagePO> images=imageDao.listImagesByProjectIdAndUserId("2018-04-15_20-30-49","boogie");
        images.get(0).getMarkers().get(0).setCurrentImg(false);
        images.get(0).getMarkers().get(1).setCurrentImg(false);
        images.get(1).getMarkers().get(0).setCurrentImg(false);
        images.get(1).getMarkers().get(1).setCurrentImg(true);

        imageDao.updateImage(images.get(0));
        imageDao.updateImage(images.get(1));

    }
    @Test
    public void testSaveProjectImages(){

         // imageDao.saveProjectImages("2018-04-15_20-30-49",imagePOs,imageFlowPOS);
    }
    @Test
    public void testQueryImageByImageId(){
        ImagePO image=imageDao.queryImageByImageId("2018-04-15_20-31-47--0");
        Assert.assertEquals("2018-04-15_20-31-47--0.jpg",image.getFileName());

    }
    @Test
    public void testQueryImagesByProjectId(){

        ArrayList<ImagePO> images=imageDao.listImagesByProjectId("2018-04-15_20-30-49");
        Assert.assertEquals(2,images.size());
        Assert.assertEquals("2018-04-15_20-31-47--0",images.get(0).getImageId());
        Assert.assertEquals("2018-04-15_20-31-47--1",images.get(1).getImageId());
    }
    @Test
    public void testQueryImagesByProjectIdAndUserId(){

        ArrayList<ImagePO> images=imageDao.listImagesByProjectIdAndUserId("2018-04-15_20-30-49","boogie");
        Assert.assertEquals(2,images.size());
        Assert.assertEquals("2018-04-15_20-31-47--0",images.get(0).getImageId());
        Assert.assertEquals("2018-04-15_20-31-47--1",images.get(1).getImageId());
        Assert.assertEquals("boogie",images.get(0).getMarkers().get(1).getUsername());
        ArrayList<ImagePO> images2=imageDao.listImagesByProjectIdAndUserId("2018-04-15_20-30-49","boogie",1,1);
       Assert.assertEquals(1,images2.size());
        Assert.assertEquals("2018-04-15_20-31-47--1",images2.get(0).getImageId());
      // Assert.assertEquals("2018-03-20_12-32-08--1",images.get(1).getImageId());
    }

    @Test
    public void testQueryCurrentImagesByProjectIdAndUserId(){
        ArrayList<ImagePO> imagePOsOfBoogie= imageDao.listCurrentImagesByProjectIdAndUserId("2018-04-15_20-30-49","boogie",0,2);
        ArrayList<ImagePO> imagePOsOfBeiBei= imageDao.listCurrentImagesByProjectIdAndUserId("2018-04-15_20-30-49","beibei",0,2);

        Assert.assertEquals(1,imagePOsOfBoogie.size());
        Assert.assertEquals("2018-04-15_20-31-47--1",imagePOsOfBoogie.get(0).getImageId());

      //  Assert.assertEquals(2,imagePOsOfBeiBei.size());
      //  Assert.assertEquals("2018-04-15_20-31-47--0",imagePOsOfBeiBei.get(0).getImageId());

        Assert.assertEquals(null,imagePOsOfBeiBei);

    }

    @Test
    public void testUpdateImage(){

        ImagePO image=imageDao.queryImageByImageId("2018-03-20_12-32-08--0");
        image.setFileName("D:\\Homework\\软工三大作业\\summerCS_Phase_I\\data\\ImageFlow/2018-03-20_12-32-08--0.jpg");
        imageDao.updateImage(image);
    }

    @Test
    public void testListLauncherImagesByProjectId(){
          ArrayList<ImagePO> images=imageDao.listLauncherImagesByProjectId("2018-04-15_20-30-49",0,1);
          Assert.assertEquals(1,images.size());
          Assert.assertEquals("2018-04-15_20-31-47--0",images.get(0).getImageId());
          //Assert.assertEquals("2018-03-20_12-32-08--1",images.get(1).getImageId());
    }

}
