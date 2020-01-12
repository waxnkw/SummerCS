package com.example.summer.daoImpl;

import com.example.summer.configInfo.DataAbsolutePath;
import com.example.summer.dao.ImageDao;
import com.example.summer.dao.ImageFlowDao;
import com.example.summer.dao.ProjectDao;
import com.example.summer.domain.ImagePO;
import com.example.summer.domain.ProjectPO;
import com.example.summer.utility.dataUtility.InitTimeUtility;
import com.example.summer.utility.dataUtility.ZipFileUtility;
import com.example.summer.utility.json.JsonStringUitility;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageFlowDaoImpl implements ImageFlowDao {

    private static final String IMAGE_FLOW_PATH = DataAbsolutePath.DATA_PATH + "ImageFlow"+File.separator;
    private static  final String TRAIN = "train";
    private static  final String TEST = "test";
    private static  final String ZIP_SURFIX = ".zip";

    private ImageDao imageDao = new ImageDaoImpl();

    @Override
    public boolean saveProjectImages( MultipartFile imagesFile, String projectId) {
        String path = IMAGE_FLOW_PATH+projectId + ZIP_SURFIX;
        System.out.println("path"+path);

        //将压缩包存储到ImageFLowPath
        try {
            imagesFile.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //unzip
        ZipFileUtility.unZipFiles(path,IMAGE_FLOW_PATH);

        //init respond ImagePO
        File file = new File(IMAGE_FLOW_PATH+projectId);
        String fileDirName = "";
        for (String s : file.list()){
            if(!s.contains("__MACOSX")){ fileDirName = s ;}
        }
        String trainPath = projectId +File.separator+fileDirName + File.separator + TRAIN;
        String testPath = projectId +File.separator+fileDirName + File.separator + TEST;
        initTrainImages(trainPath, projectId);
        initTestImages(testPath, projectId);

        return true;
    }

    /**
     * 初始化训练数据
     * */
    private void initTrainImages(String path, String projectId){
        initImages(path, TRAIN, projectId);
    }

    /**
     * 初始化测试数据
     * */
    private void initTestImages(String path, String projectId){
        initImages(path, TEST, projectId);
    }

    /**
     * 初始化图片
     * */
    public void initImages(String path, String split, String projectId){
        ArrayList<ImagePO> imageList = new ArrayList<>();
        String [] fileNames = new File(IMAGE_FLOW_PATH+path).list();
        if (fileNames==null){return;}
        int upUserLimit = queryUpUserLimit(projectId);
        for(int i=0;i< fileNames.length; i++){
            String id = generateImagePOID(i+"");
            String imgFileName =  path + File.separator + fileNames[i];
            ImagePO image = ImagePO.builder()
                    .upUserLimit(upUserLimit)
                    .projectId(projectId)
                    .split(split)
                    .imageId(id)
                    .fileName(imgFileName)
                    .build();
            imageList.add(image);
            //System.out.println("singlepath:"+imgFileName);
            System.out.println("list:"+ JsonStringUitility.toString(imageList));
        }
        imageDao.saveProjectImages(projectId, imageList);
    }

    /**
     * 查询标记用户上限
     * */
    private int queryUpUserLimit(String projectId){
        ProjectDao projectDao = new ProjectDaoImpl();
        ProjectPO project = projectDao.queryProjectById(projectId);
        return project.getUpUserLimit();
    }

    /**
     * 生成图片Id
     * */
    private String generateImagePOID(String indexStr){
        //indexStr is to tag them because the speed of computer is too fast and they will be created at the same time
        String id= InitTimeUtility.getCurrentTime()+"--"+indexStr;
        return id;
    }

}
