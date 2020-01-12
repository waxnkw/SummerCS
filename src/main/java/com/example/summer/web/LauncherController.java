package com.example.summer.web;
import com.example.summer.model.LauncherImageModel;
import com.example.summer.model.LauncherProjectInfoModel;
import com.example.summer.model.ProjectInfoModel;
import com.example.summer.model.SimpleProjectModel;

import com.example.summer.service.LauncherService;
import com.example.summer.service.RankService;
import com.example.summer.service.impl.RankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class LauncherController {
    @Autowired
    private LauncherService launcherService;
    private RankService rankService = new RankServiceImpl();

    /**
     * 创建普通项目
     * @state 粗略测试通过
     * @param username 用户名
     * @param project 项目信息
     * @return 项目Id
     * */
    @RequestMapping("/launcher/launch/simple")
    public String createSimpleProject( @RequestParam("username") String username,@RequestBody SimpleProjectModel project){
        String projectId = launcherService.createProject(username,project);
        rankService.createProjectRank(projectId);
        return  projectId;
    }

    /***
     * 上传图片集
     * @state 未完成
     * */
    @RequestMapping(value = "/launcher/upload", method =  RequestMethod.POST)
    public boolean uploadImages (@RequestParam("projectId") String projectId,@RequestParam MultipartFile picData, HttpServletRequest request) {
        return launcherService.uploadImages(projectId, picData);
    }

    /**
     * 关闭项目
     * @state 粗略测试通过
     * @param projectId 项目Id
     * @return 是否关闭项目成功
     * */
    @RequestMapping("/launcher/close")
    public boolean closeProject(@RequestParam("projectId") String projectId){
        return launcherService.closeProject(projectId);
    }

    /**
     * 查询一个发布的项目的具体信息
     * @param projectId 项目的Id
     * @param username  用户名检测该用户是否在项目当中
     * @return 给发起者查看的项目信息
     * */
    @RequestMapping("/launcher/queryLauncherProjInfo")
    public LauncherProjectInfoModel queryLauncherProjectInfoByProjectId(
            @RequestParam("projectId") String projectId
            , @RequestParam("username") String username){
        return launcherService.queryLauncherProjectInfoByProjectId(projectId ,username);
    }

    /**
     * 根据项目Id查询项目内的部分图片
     * @state 未实现
     * @param projectId 项目的Id
     * @param begin 图片起始位置
     * @param num 此次查询图片数量
     * @return 给发起者查看的项目信息
     * */
    @RequestMapping("/launcher/listLauncherImages")
    public ArrayList<LauncherImageModel>  listLauncherImagesByProjectId(@RequestParam("projectId") String projectId
            ,@RequestParam("begin") int begin
            ,@RequestParam("num") int num){
        return  launcherService.listLauncherImagesByProjectId(projectId, begin, num);
    }

    /**
     * 根据项目Id和用户名得到一个用户发布的项目清单
     * @state 未完成
     *@param username 用户名
     *@param projectId 项目Id
     * @return 项目的简略信息清单
     * */
    @RequestMapping("/launcher/listLaunchedProjs")
    public ArrayList<ProjectInfoModel> listLaunchedProjectInfosByProjectIdAndUserName(String projectId, String username){
        return  launcherService.listLaunchedProjectInfosByProjectIdAndUserName(projectId, username);
    }
}
