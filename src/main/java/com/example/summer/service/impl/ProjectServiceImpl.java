package com.example.summer.service.impl;

import com.example.summer.model.ImageListModel;
import com.example.summer.model.LauncherProjectInfoModel;
import com.example.summer.model.ProjectInfoModel;
import com.example.summer.model.ProjectModel;
import com.example.summer.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/*
 * @program: summerCS_Phase_I
 * @description:
 * @author: Mr.Wang
 * @create: 2018/3/21
 */
@Service
public class ProjectServiceImpl implements ProjectService {


    /**
     * 储存一个项目的信息
     * @param projectModel 项目信息
     * @return
     */
    @Override
    public String saveProject(ProjectModel projectModel) {
        return projectModel.save();
    }

    /*
    * @Description: 查询所有项目
    * @Param: []
    * @return: java.util.ArrayList<com.example.summer.model.ProjectModel> 所有可访问项目
    * @Author: Mr.Wang
    * @Date: 2018/3/21
    */
    @Override
    public ArrayList<ProjectModel> queryAllProjects() {
        return  ProjectModel.listAllProjects();
    }

    /* 
    * @Description: 根据项目ID查询项目信息 
    * @Param: [projectId]
    * @return: com.example.summer.model.ProjectModel 
    * @Author: Mr.Wang 
    * @Date: 2018/3/21 
    */ 
    @Override
    public ProjectModel queryProjectById(String projectId) {
        return ProjectModel.queryProjectById(projectId);
    }

    /*
    * @Description: 根据用户名查询其发起的项目
    * @Param: [userName]
    * @return: java.util.ArrayList<com.example.summer.model.LauncherProjectInfoModel>
    * @Author: Mr.Wang
    * @Date: 2018/3/21
    */
    @Override
    public ArrayList<LauncherProjectInfoModel> queryLaunchedProjectsByUserName(String userName) {
        ArrayList<ProjectModel> projectModels = ProjectModel.listLaunchedProjectsByUserName(userName);
        if(projectModels!=null){
            ArrayList<LauncherProjectInfoModel> launcherProjectInfoModels = new ArrayList<>();
            projectModels.forEach(e->launcherProjectInfoModels.add(LauncherProjectInfoModel.convert(e)));
            return launcherProjectInfoModels;
        }
        return null;
    }

    /*
    * @Description: 根据用户名查询其参加的项目
    * @Param: [userName]
    * @return: java.util.ArrayList<com.example.summer.model.LauncherProjectInfoModel>
    * @Author: Mr.Wang
    * @Date: 2018/3/21
    */ 
    @Override
    public ArrayList<ProjectInfoModel> queryJoinedProjectsByUserName(String userName) {
        ArrayList<ProjectInfoModel> projects = ProjectInfoModel.listJoinedProjectInfos(userName);
        if(projects!=null){
            return projects;
        }
        return null;
    }

    /**
     * 查询项目内部能够被分配的图片数量
     * @param projectId 项目的Id
     * */
    @Override
    public int getCanBeAssignedImagesNum(String projectId,String username){
        return ImageListModel.calcCanBeAssignedImagesNum(projectId,username);
    }
}
