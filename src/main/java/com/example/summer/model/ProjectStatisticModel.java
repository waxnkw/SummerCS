package com.example.summer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProjectStatisticModel {
    private int totalNum ;
    private int doneNum;
    private int doingNum;

    /***************************************************************************
     *                                                                         *
     * static methods                                                            *
     *                                                                         *
     **************************************************************************/
    /**
     * 生成当前的所有项目的统计信息
     * @return 所有项目的统计信息
     * */
    public static ProjectStatisticModel generate(){
        ArrayList<ProjectModel> projectList = ProjectModel.listAllProjects();
        int totalNum = projectList.size();
        int doingNum = calcDoingNum(projectList);
        int doneNum = totalNum-doingNum;
        ProjectStatisticModel projectStatisticModel
                = ProjectStatisticModel.builder()
                .totalNum(totalNum)
                .doneNum(doneNum)
                .doingNum(doingNum).build();
        return projectStatisticModel;
    }

    /**
     * 计算完成项目数目
     * @param projectList 需要计算的项目清单
     * */
    private static int calcDoingNum(ArrayList<ProjectModel> projectList){
        int doneNum = 0;
        for(ProjectModel project: projectList){
            if(project.isCanBeJoined()){doneNum++;}
        }
        return doneNum;
    }
}
