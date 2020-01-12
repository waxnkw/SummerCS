package com.example.summer.model;

import com.example.summer.dao.ImageFlowDao;
import com.example.summer.daoImpl.ImageFlowDaoImpl;
import org.springframework.web.multipart.MultipartFile;

public class ImageFlowModel {


    private static ImageFlowDao imageFlowDao = new ImageFlowDaoImpl();
    /**
     * 存储project的一批图片
     * */
    public static boolean saveProjectImages(MultipartFile picData, String projectId){
        return imageFlowDao.saveProjectImages(picData, projectId);
    }
}
