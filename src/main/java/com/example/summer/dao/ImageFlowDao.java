package com.example.summer.dao;

import org.springframework.web.multipart.MultipartFile;

public interface ImageFlowDao extends  Dao {

    /**
     *存储project的图片
     * @param projectId 项目Id
     * @param imagesFile 图片的压缩包
     *  @return 是否储存成功
     * */
    public boolean saveProjectImages( MultipartFile imagesFile, String projectId);
}
