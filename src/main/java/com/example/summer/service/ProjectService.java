package com.example.summer.service;

import com.example.summer.model.LauncherProjectInfoModel;
import com.example.summer.model.ProjectInfoModel;
import com.example.summer.model.ProjectModel;

import java.util.ArrayList;

/*
 * @program: summerCS_Phase_I
 * @description: 关于项目的操作
 * @author: 161250140 wny
 * @create: 2018/3/15
 **/
public interface ProjectService {

    /**
     * 储存一个项目的信息
     * @param projectModel 项目信息
     * @return
     */
    public String saveProject(ProjectModel projectModel);

    /**
     * 查询所有项目
     * @return 所有可访问项目
     * */
    public ArrayList<ProjectModel> queryAllProjects();

    /**
     * 根据项目Id查询项目信息
     * @param projectId  项目Id
     * @return 项目信息
     * */
    public ProjectModel queryProjectById(String projectId);

    /**
     * 根据用户名查询其发起的项目
     * @param userName  用户名
     * @return 新标记的图片
     * */
    public ArrayList<LauncherProjectInfoModel> queryLaunchedProjectsByUserName(String userName);

    /**
     * 根据用户名查询其参加的项目
     * @param userName  用户名
     * @return 新标记的图片
     * */
    public ArrayList<ProjectInfoModel> queryJoinedProjectsByUserName(String userName);

    /**
     * 查询项目内部能够被分配的图片数量
     * @param projectId 项目的Id
     * @param username 用户名称
     * */
    public int getCanBeAssignedImagesNum(String projectId,String username);
}
