package com.example.summer.debug;

import com.example.summer.configInfo.DataAbsolutePath;
import com.example.summer.model.ProjectModel;
import com.example.summer.service.impl.ProjectServiceImpl;
import com.example.summer.utility.json.JsonStringUitility;

import java.util.ArrayList;

public class SeeProjects {

    private static ProjectServiceImpl projectService = new ProjectServiceImpl();

    public static void seeProjects() {
        DataAbsolutePath.DEBUG();
        ArrayList<ProjectModel> projectModels = projectService.queryAllProjects();
        System.out.println(JsonStringUitility.toString(projectModels));
    }

    public static void main(String [] args){
        seeProjects();
    }
}
