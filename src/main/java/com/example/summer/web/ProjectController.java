package com.example.summer.web;

import com.example.summer.model.LauncherProjectInfoModel;
import com.example.summer.model.ProjectInfoModel;
import com.example.summer.model.ProjectModel;
import com.example.summer.model.WorkerProjectInfoModel;
import com.example.summer.service.LauncherService;
import com.example.summer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/*
 * @program: summerCS_Phase_I
 * @description: LogCotroller
 * @author: 161250193
 * @create: 2018/3/17
 */
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 查询所有项目
     * @return 所有可访问项目
     * */
    @RequestMapping("/proj/allprojects")
    public ArrayList<ProjectModel> queryAllProjects(){
        //TODO
        return  projectService.queryAllProjects();
    }

    /**
     * 根据项目Id查询项目信息
     * @param projectId  项目Id
     * @return 项目信息
     * */
    @RequestMapping("/proj/query{projectId}")
    public ProjectModel queryProjectById(@PathVariable("projectId") String projectId){
        //TODO
        return  projectService.queryProjectById(projectId);
    }


    /**
     * 查询项目内部能够被分配的图片数量
     * @param projectId 项目的Id
     * */
    @RequestMapping("/proj/imageleft")
    public int getCanBeAssignedImagesNum(@RequestParam("projectId") String projectId, @RequestParam("username")String username){
        return  projectService.getCanBeAssignedImagesNum(projectId, username);
    }
}
