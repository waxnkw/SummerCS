package com.example.summer.serviceTest;

import com.example.summer.model.ProjectModel;
import com.example.summer.model.SimpleProjectModel;
import com.example.summer.service.LauncherService;
import com.example.summer.stub.SimpleProjectStub;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class LauncherServiceTest {

    @Before
    public void doBefore() {
        ProjectModel projectModel = ProjectModel.queryProjectById("012345");
        if(projectModel!=null){
            projectModel.setCanBeJoined(true);
        }
    }


    @Test
    public void createProject() {
        ProjectModel project = SimpleProjectStub.generateProjectModel();
        String Id = project.save();
        ProjectModel newProject = ProjectModel.queryProjectById(Id);
        project.setProjectId(Id);
        String projectStr = net.sf.json.JSONObject.fromObject(project).toString();
        String newProjectStr = net.sf.json.JSONObject.fromObject(newProject).toString();
        Assert.assertEquals(projectStr, newProjectStr);
    }

    @Test
    public void closeProject() {
        ProjectModel project = ProjectModel.queryProjectById("2018-04-15_16-20-14");
        if(project!=null){
            project.setCanBeJoined(false);
            project.update();
        }
        String projectStr = net.sf.json.JSONObject.fromObject(project).toString();
        String newProjectStr = net.sf.json.JSONObject.fromObject(ProjectModel.queryProjectById("2018-04-15_16-20-14")).toString();
        Assert.assertEquals(projectStr, newProjectStr);
    }
}