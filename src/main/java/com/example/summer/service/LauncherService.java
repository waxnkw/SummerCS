package com.example.summer.service;

import com.example.summer.model.LauncherImageModel;
import com.example.summer.model.LauncherProjectInfoModel;
import com.example.summer.model.ProjectInfoModel;
import com.example.summer.model.ProjectModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface LauncherService {
    /**
     * 创建项目
     * 1. 创建项目(生成id)
     * 2. 返回id
     * @param username 用户名
     * @param project 项目信息
     * @return 项目Id
     * */
    public String createProject(String username, ProjectModel project);

    /***
     * 上传图片集
     * @param projectId 项目Id
     * @param picData 图片压缩包文件
     * */
    public boolean uploadImages (String projectId,MultipartFile picData) ;

    /**
     * 关闭项目
     * 1.得到项目
     * 2.update该项目项目信息为关闭状态
     * @param projectId 项目Id
     * @return 是否关闭项目成功
     * */
    public boolean closeProject(String projectId);

    /**
     * 查询一个发布的项目的具体信息
     * @state 未实现
     * @param projectId 项目的Id
     * @return 给发起者查看的项目信息
     * */
    public LauncherProjectInfoModel queryLauncherProjectInfoByProjectId(String projectId, String username);

    /**
     * 根据项目Id查询项目内的部分图片
     * @state 未实现
     * @param projectId 项目的Id
     * @param begin 图片起始位置
     * @param num 此次查询图片数量
     * @return 给发起者查看的项目信息
     * */
    public ArrayList<LauncherImageModel> listLauncherImagesByProjectId(String projectId, int begin, int num);

    /**
     * 根据项目Id和用户名得到一个用户发布的项目清单
     *@param username 用户名
     *@param projectId 项目Id
     * @return 项目的简略信息清单
     * */
    public ArrayList<ProjectInfoModel> listLaunchedProjectInfosByProjectIdAndUserName(String projectId, String username);
}
