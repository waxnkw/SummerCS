package com.example.summer.driver;

import com.example.summer.configInfo.DataAbsolutePath;
import com.example.summer.dao.ImageDao;
import com.example.summer.daoImpl.ImageDaoImpl;
import com.example.summer.domain.ImagePO;
import com.example.summer.utility.dataUtility.InitTimeUtility;
import com.example.summer.utility.dataUtility.ReadAndWritePOsUtility;
import com.example.summer.utility.json.JsonStringUitility;

import java.io.File;
import java.util.ArrayList;

public class ImageFlowDaoDriver {
    /**
     * 生成图片Id
     * */
    private static String generateImagePOID(String indexStr){
        //indexStr is to tag them because the speed of computer is too fast and they will be created at the same time
        String id= InitTimeUtility.getCurrentTime()+"--"+indexStr;
        return id;
    }

    public static void createImages(String projectId){
        DataAbsolutePath.DEBUG();
        ArrayList<ImagePO> imageList = new ArrayList<>();
        String [] fileNames = new File(DataAbsolutePath.DATA_PATH+"ImageFlow/proj/train/").list();
        if (fileNames==null){return;}
        for(int i=0;i< fileNames.length; i++){
            String id = generateImagePOID(i+"");
            String imgFileName =  "proj/train" + File.separator + fileNames[i];
            ImagePO image = ImagePO.builder()
                    .upUserLimit(1)
                    .projectId(projectId)
                    .split("train")
                    .imageId(id)
                    .fileName(imgFileName).build();
            imageList.add(image);
            System.out.println("list:"+ JsonStringUitility.toString(imageList));
        }
        System.out.println(DataAbsolutePath.DATA_PATH+"Image");
        ReadAndWritePOsUtility.writePos(imageList, DataAbsolutePath.DATA_PATH+"Image");
    }

    public static  void main(String [] args){
       ImageFlowDaoDriver.createImages("2018-04-28_21-10-09-350");
    }
}
