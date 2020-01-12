package com.example.summer.model;


import com.example.summer.utility.dateUtility.DateUtility;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 项目的简略信息
 * 包括项目的id name description
 * @author zhangao
 * @version 2018.4.19
 */
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProjectInfoModel {
    private String projectId;
    private String projectName;
    private String description;
    private String launcherName;
    private String createDate;

    /**
     * 获得所有可参加的项目的列表
     * @return ArrayList<ProjectInfoModel>
     */
    public static ArrayList<ProjectInfoModel> listCanbeJoinedProjectInfos(){
        ProjectInfoModel infoModel = new ProjectInfoModel();
        ArrayList<ProjectModel> projectModels = ProjectModel.listCanbeJoinedProjectInfos();
        ArrayList<ProjectInfoModel> projectInfoModels = new ArrayList<>();
        if (projectModels != null){
            for(ProjectModel project:projectModels){
                projectInfoModels.add(infoModel.generate(project));
            }
            return projectInfoModels;
        }
        return null;
    }

    /**
     * 获取“我参与的项目”的列表
     * @author Mr.Wang
     * @param username username
     * @return java.util.ArrayList<com.example.summer.model.ProjectInfoModel>
     */
    public static ArrayList<ProjectInfoModel> listJoinedProjectInfos(String username){
        ArrayList<ProjectModel> projectModels = ProjectModel.listJoinedProjectsByUserName(username);
        if(projectModels!=null){
            ProjectInfoModel infoModel = new ProjectInfoModel();
            ArrayList<ProjectInfoModel> projectInfoModels = new ArrayList<>();
            projectModels.forEach(e->projectInfoModels.add(infoModel.generate(e)));
            return projectInfoModels;
        }
        return null;
    }

    /**
     * 获取“我参与的项目”的列表,没有close的项目
     * @author Mr.Wang
     * @param username username
     * @return java.util.ArrayList<com.example.summer.model.ProjectInfoModel>
     */
    public static ArrayList<ProjectInfoModel> listNowJoinedProjectInfos(String username){
        ArrayList<ProjectModel> projectModels =  ProjectModel.listNowJoinedProjectsByUserName(username);
        ArrayList<ProjectInfoModel> projectInfoModels = new ArrayList<>();
        if(projectModels!=null){
            ProjectInfoModel infoModel = new ProjectInfoModel();
            projectModels.forEach(e->projectInfoModels.add(infoModel.generate(e)));
            return projectInfoModels;
        }
        return null;
    }


    /**
     * 获取“我发布的项目”的列表
     * 不包括已经close的
     * @author Mr.Wang
     * @param username username
     * @return java.util.ArrayList<com.example.summer.model.ProjectInfoModel>
     */
    public static ArrayList<ProjectInfoModel> listLaunchedProjectInfosByUserName(String username){
        ProjectInfoModel infoModel = new ProjectInfoModel();
        ArrayList<ProjectModel> projectModels = ProjectModel.listLaunchedProjectsByUserName(username);
        ArrayList<ProjectInfoModel> projectInfoModels = new ArrayList<>();
        if (projectModels != null){
            for(ProjectModel project:projectModels){
                if (!project.isCanBeJoined()){continue;}
                projectInfoModels.add(infoModel.generate(project));
            }
            return projectInfoModels;
        }
        return null;
    }

    /**
     * 转化ProjectModel和ProjectInfoModel
     * @author Mr.Wang
     * @param projectModel ProjectModel
     * @return com.example.summer.model.ProjectInfoModel
     */
    private ProjectInfoModel generate(ProjectModel projectModel){
        return ProjectInfoModel.builder()
                .projectId(projectModel.getProjectId())
                .projectName(projectModel.getProjectName())
                .description(projectModel.getDescription())
                .launcherName(projectModel.getLauncher().getUsername())
                .createDate(DateUtility.convertIdToDate(projectModel.getProjectId()))
                .build();
    }
}
