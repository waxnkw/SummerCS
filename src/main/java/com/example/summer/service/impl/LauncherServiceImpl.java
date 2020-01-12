package com.example.summer.service.impl;

import com.example.summer.dao.ProjectDao;
import com.example.summer.domain.ProjectPO;
import com.example.summer.model.*;
import com.example.summer.service.LauncherService;
import com.example.summer.utility.dataUtility.ConvertModelAndPOUtility;
import com.example.summer.utility.json.JsonStringUitility;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

/*
 * @program: summerCS_Phase_I
 * @description:
 * @author: Mr.Wang
 * @create: 2018/3/22
 */
@Service
public class LauncherServiceImpl implements LauncherService {

    /*
    * @Description: 创建项目
    * @Param: [username, project]
    * @return: java.lang.String
    * @Author: Mr.Wang
    * @Date: 2018/3/22
    */
    @Override
    public String createProject(String username, ProjectModel project) {
        System.out.println(JsonStringUitility.toString(project));
        if(project!=null){
            UserInfoModel userInfoModel = new UserInfoModel();
            userInfoModel.setUsername(username);
            project.setLauncher(userInfoModel);
            project.setCanBeJoined(true);
            return project.save();
        }
        return null;
    }

    @Override
    public boolean uploadImages(String projectId, MultipartFile picData) {
        return ImageFlowModel.saveProjectImages(picData,projectId);
    }

    /*
    * @Description: 关闭项目
    * @Param: [projectId]
    * @return: boolean
    * @Author: Mr.Wang
    * @Date: 2018/3/22
    */
    @Override
    public boolean closeProject(String projectId) {
        if(ProjectModel.queryProjectById(projectId)!=null){
            ProjectModel project = ProjectModel.queryProjectById(projectId);
            project.setCanBeJoined(false);
            return project.update();
        }
        return false;
    }

    @Override
    public LauncherProjectInfoModel queryLauncherProjectInfoByProjectId(String projectId, String username) {
        LauncherProjectInfoModel launcherProjectInfoModel;
        if((launcherProjectInfoModel=LauncherProjectInfoModel.generate(projectId, username))!=null){
            return launcherProjectInfoModel;
        }
        return null;
    }

    @Override
    public ArrayList<LauncherImageModel> listLauncherImagesByProjectId(String projectId, int begin, int num) {
        ArrayList<LauncherImageModel> launcherImageModels = LauncherImageModel.listLauncherImagesByProjectId(projectId, begin, num);
        if(launcherImageModels!=null){
            return launcherImageModels;
        }
        return null;
    }

    @Override
    public ArrayList<ProjectInfoModel> listLaunchedProjectInfosByProjectIdAndUserName(String projectId, String username) {
        //暂时不使用projectId
        ArrayList<ProjectInfoModel> projectInfoModels = ProjectInfoModel.listLaunchedProjectInfosByUserName(username);
        if(projectInfoModels!=null){
            return projectInfoModels;
        }
        return null;
    }
}
